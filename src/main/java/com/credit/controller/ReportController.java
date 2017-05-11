package com.credit.controller;

import com.credit.dao.FlowMapper;
import com.credit.model.Apply;
import com.credit.model.Flow;
import com.credit.model.Report;
import com.credit.service.IApplyService;
import com.credit.service.IFlowService;
import com.credit.service.IReportService;
import com.credit.utils.ExcelUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private Logger logger= LoggerFactory.getLogger(ReportController.class);

    @RequestMapping(value = "/manage/{name}/{role}",method = RequestMethod.GET)
    public String Manage(@PathVariable("name")String name,
                         @PathVariable("role")String role,
                         Model model){
        logger.info("报表管理，姓名：{},角色:{}",name,role);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        return "report_manage";
    }

    @RequestMapping(value = "/total",method = RequestMethod.GET)
    public String Total(Model model){
        List<Object> list=flowService.selectAll();
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
            }
        }
        for (Report report:reportList) {
           if(reportService.selectByApplyId(report.getApplyid()).equals(null))
            reportService.insert(report);
        }
        model.addAttribute("totalCost_success","计算完毕。");
        return "report_manage";
    }

    @RequestMapping(value = "/total",method = RequestMethod.POST)
    public String Total(@RequestParam("reportField") String [] fields,
                        @RequestParam("fileName")String fileName,Model model){

        List<String []> list=new ArrayList<String[]>();

        List<Object> reportList=reportService.selectAll();

        for (Object object:reportList){
            String [] reports=new String[5];
            Report report=(Report)object;
            Apply apply=applyService.selectByPrimaryKey(report.getApplyid());
//          根据前台传来的字段进行报表字段筛选
            for (int i=0;i<fields.length;i++)
            {
                logger.info("reportField:{}",fields[i]);
                if(fields[i].equals("applyId"))
                    reports[i]=report.getApplyid();
                else if(fields[i].equals("applyName"))
                    reports[i]=apply.getApplyname();
                else if(fields[i].equals("applyMember"))
                    reports[i]=apply.getApplymember();
                else if(fields[i].equals("applyDate"))
                    reports[i]=apply.getApplydate();
                else if(fields[i].equals("flowCost"))
                    reports[i]= String.valueOf(report.getFlowcost());
                else {}
            }
            list.add(reports);
        }
        String path="E:\\CanFintech\\project\\manage\\src\\main\\resources\\download";
        //生成excel表格文件
        try {
            ExcelUtils.writer(path,fileName,"xls",list,fields);
            model.addAttribute("fileName",fileName);
            model.addAttribute("report_success","报表生成完成，");
        } catch (IOException e) {
            model.addAttribute("report_success","报表生成发生错误，请重试！");
            e.printStackTrace();
        }
        return "report_manage";
    }

    @RequestMapping(value = "/download/{fileName}",produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@PathVariable("fileName")String fileName) throws IOException {

        File file = new File("E:\\CanFintech\\project\\manage\\src\\main\\resources\\download\\"+fileName+".xls");

        String dfileName = fileName+".xls";
//                下面开始设置HttpHeaders,使得浏览器响应下载
        HttpHeaders headers = new HttpHeaders();
//                设置响应方式
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                设置响应文件
        headers.setContentDispositionFormData("attachment", dfileName);
//                把文件以二进制形式写回
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
