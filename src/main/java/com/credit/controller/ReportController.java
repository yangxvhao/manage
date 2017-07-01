package com.credit.controller;

import com.credit.model.*;
import com.credit.service.IApplyService;
import com.credit.service.IFlowService;
import com.credit.service.IReportService;
import com.credit.service.IUserService;
import com.credit.utils.ExcelUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date：2017/5/10
 * @author:yangxvhao
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired(required = false)
    IReportService reportService;

    @Autowired(required = false)
    IFlowService flowService;

    @Autowired(required = false)
    IApplyService applyService;

    @Autowired(required = false)
    IUserService userService;

    private Logger logger= LoggerFactory.getLogger(ReportController.class);


    @RequestMapping(value = "/manage",method = RequestMethod.GET)
    public String Manage(Model model, HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("User");
        logger.info("报表管理,name:{},role:{}",user.getName(),user.getRole());


        if(user.getRole().equals("办单员")){
            model.addAttribute("report_error","您是办单员，暂无权限！");
            return "success";
        }else {

            return "report_manage";
        }
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public String Count(Model model){
        List<Object> userList=userService.selectAll();
        List<String[]> list=new ArrayList<String[]>();
//        Map<String,String> map=new HashMap<String, String>();
        for (Object object:userList){
            User user1=(User) object;
            if(user1.getRole().equals("办单员")){
                logger.info("办单员姓名：{}",user1.getName());
                List<Object> applys=applyService.selectByApplyMember(user1.getName());
                list.add(Count(applys));
//                map.put(user1.getName(), String.valueOf(applys.size()));
            }
        }

        logger.info("统计结果:{}",list.get(0).toString());
        model.addAttribute("nameList",list);
        return "count_report";
    }

    public String [] Count(List<Object> list){
        int count=0;
        int sum=list.size();
        int over_sum=0;
        String name="";
        double passRate=0.0;
        for (Object o:list){
            Apply apply=(Apply)o;
            if(apply.getStatus().equals("审批结束")) {
                over_sum++;
                if (apply.getCheckmoney()!=0) {
                    count++;
                }
            }
            name=apply.getApplymember();
        }
        if(over_sum!=0){
            passRate=(count*1.0)/(over_sum*1.0);
            String [] fields=new String[]{name, String.valueOf(sum), String.valueOf(over_sum),
                    String.valueOf(count), String.valueOf(passRate)};
            logger.info("统计结果:{}",fields.toString());
            return fields;
        }else {
            String [] fields=new String[]{name, String.valueOf(sum), String.valueOf(over_sum),
                    String.valueOf(count), "0.0"};
            logger.info("统计结果:{}",fields.toString());
            return fields;
        }


    }

    @RequestMapping(value = "/total",method = RequestMethod.GET)
    public String Total(Model model){
        List<Object> list=flowService.selectAll();
        logger.info("list:{}",list.size());
        if(list.size()==0){
            model.addAttribute("totalCost_success","没有要计算的流程，请确认已导入流程表！");
            return "report_manage";
        }
        List<Report> reportList=new ArrayList<Report>();
        Double tatol=0.0;
        for (int i=0;i<list.size()-1;i++){
            Flow flow=(Flow) list.get(i);
            Flow flowNext=(Flow) list.get(i+1);
            if(flow.getApplyid().equals(flowNext.getApplyid())){
                tatol=tatol+flow.getFlowprice();
            }else {
                Report report=new Report();
                report.setApplyid(flow.getApplyid());
                tatol=tatol+flow.getFlowprice();
                report.setFlowcost(tatol);
                reportList.add(report);
                tatol=0.0;
            }if(i==list.size()-2){
                Report report=new Report();
                report.setApplyid(flowNext.getApplyid());
                tatol=tatol+flowNext.getFlowprice();
                report.setFlowcost(tatol);
                reportList.add(report);
                tatol=0.0;
            }
        }
        logger.info("reportList:{}",reportList.size());
        for (Report report:reportList) {

            if(reportService.selectAll().size()==0){
                reportService.insert(report);
            }//已经存在的不添加
            else if(reportService.selectByApplyId(report.getApplyid())==(null)) {
                reportService.insert(report);
            }
        }
        model.addAttribute("totalCost_success","计算完毕。<a href=\"/report/show\">查看</a>");
        return "report_manage";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String Show(Model model){
        List<Object> reportList=reportService.selectAll();
        logger.info("reportList.size:{}",reportList.size());
        model.addAttribute("list",reportList);
        return "show_total";
    }

    @RequestMapping(value = "/total/{preview}",method = RequestMethod.POST)
    public String Total(@PathVariable("preview")String isPreview,
                        @RequestParam("reportField") String [] fields,
                        @RequestParam("fileName")String fileName,Model model,
                        HttpServletRequest request){

        List<String []> list=new ArrayList<String[]>();

        List<Object> reportList=reportService.selectAll();
        logger.info("reportList.size:{}",reportList.size());
        if(reportList.size()==0){
            model.addAttribute("report_success","请先计算流程总价！");
            return "report_manage";
        }

        String [] titiles=new String[7];

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals("applyId")) {
                titiles[i] = "申请单号";
            } else if (fields[i].equals("applyName")) {
                titiles[i] = "申请人";
            } else if (fields[i].equals("applyMember")) {
                titiles[i] = "办单员";
            } else if (fields[i].equals("applyDate")) {
                titiles[i] = "申请日期";
            } else if (fields[i].equals("status")) {
                titiles[i] = "审批状态";
            } else if (fields[i].equals("result")) {
                titiles[i] = "审批结果";
            }
             else if (fields[i].equals("flowCost")) {
                titiles[i] = "审批成本";
            } else {
            }
        }
        logger.info("titiles:{}",titiles.toString());
        for (Object object:reportList) {
            String[] reports = new String[7];
            Report report = (Report) object;
            Apply apply = applyService.selectByPrimaryKey(report.getApplyid());
            if (apply != null) {
//          根据前台传来的字段进行报表字段筛选
                for (int i = 0; i < fields.length; i++) {
                    logger.info("reportField:{}", fields[i]);
                    if (fields[i].equals("applyId")) {
                        reports[i] = report.getApplyid();
                    } else if (fields[i].equals("applyName")) {
                        reports[i] = apply.getApplyname();
                    } else if (fields[i].equals("applyMember")) {
                        reports[i] = apply.getApplymember();
                    } else if (fields[i].equals("applyDate")) {
                        reports[i] = apply.getApplydate();
                    }  else if (fields[i].equals("status")) {
                        reports[i] = apply.getStatus();
                    }  else if (fields[i].equals("result")) {
                        reports[i] = apply.getResult();
                    } else if (fields[i].equals("flowCost")) {
                        reports[i] = String.valueOf(report.getFlowcost());
                    } else {
                    }
                }
                list.add(reports);
            }
        }
        if(isPreview.equals("true")){
            model.addAttribute("fields",titiles);
            model.addAttribute("list",list);
//            request.getSession().setAttribute("sublist",subList);
            return "report_preview";
        }else {
            String path = "E:\\CanFintech\\project\\manage\\src\\main\\resources\\download";
            //生成excel表格文件
            try {
                ExcelUtils.writer(path, fileName, "xls", list, titiles);
                model.addAttribute("fileName", fileName);
                model.addAttribute("report_success", "报表生成完成，");
            } catch (IOException e) {
                model.addAttribute("report_success", "报表生成发生错误，请重试！");
                e.printStackTrace();
            }
            return "report_manage";
        }
    }


    @RequestMapping(value = "/download/{fileName}",produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@PathVariable("fileName")String fileName) throws IOException {

            File file = new File("E:\\CanFintech\\project\\manage\\src\\main\\resources\\download\\" + fileName + ".xls");

            String dfileName = fileName + ".xls";
//                下面开始设置HttpHeaders,使得浏览器响应下载
            HttpHeaders headers = new HttpHeaders();
//                设置响应方式
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                设置响应文件
            headers.setContentDispositionFormData("attachment", dfileName);
//                把文件以二进制形式写回
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String download(Model model){
        model.addAttribute("report_error","请先生成报表");
        return "report_manage" ;
    }
}
