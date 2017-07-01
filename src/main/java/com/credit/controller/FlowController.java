package com.credit.controller;

import com.credit.model.Apply;
import com.credit.model.Flow;
import com.credit.model.User;
import com.credit.service.IApplyService;
import com.credit.service.IFlowService;
import com.credit.utils.ExcelUtils;
import com.sun.javafx.sg.prism.NGShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @date：2017/5/9
 * @author:yangxvhao
 */
@Controller
@RequestMapping("/flow")
public class FlowController {

    private Logger logger= LoggerFactory.getLogger(FlowController.class);

    @Autowired(required = false)
    IFlowService flowService;

    @Autowired(required = false)
    IApplyService applyService;

    @RequestMapping(value = "/manage",method = RequestMethod.GET)
    public String Upload(Model model, HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("User");
        logger.info("流程判断role:{}",user.getRole());
        if(user.getRole().equals("办单员")) {
            model.addAttribute("flow_error", "您是办单员，暂无权限！");
            return "success";
        }else {
            return "flow_manage";
        }

    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String Manage(@RequestParam("excelFile") MultipartFile multipartFile,
                         Model model,HttpServletRequest request) {

        User user=(User) request.getSession().getAttribute("User");
        logger.info("流程上传,name:{},role:{}",user.getName(),user.getRole());

        List<Object> list=new ArrayList<Object>();

        if (!user.getRole().equals("办单员")) {
            try {
                list = ExcelUtils.ExcelForList(multipartFile, Flow.class, false);
            }catch (Exception e){
                model.addAttribute("upload_error","上传出现错误，请验证格式是否正确！");
                e.getMessage();
                return "show_apply";
            }

            int sum = 0;int count = 0;int repeat=0;

            try{
                for (Object object : list) {
                    sum++;
                    Flow flow = (Flow) object;
                    if(flowService.selectByPrimaryKey(flow.getId())==(null)) {
                        flow.setFlowscale(0);
                        if (flowService.insert(flow) == 1) {
                            count++;
                        }
                        logger.info(flow.toString());
                    }else {
                        repeat++;
                    }
                }
            }
            catch (Exception e){
                model.addAttribute("upload_error","数据库存入错误，请验证格式是否正确！");
                e.printStackTrace();
                return "show_flow";
            }

            if (sum == count) {
                model.addAttribute("upload_success", "流程单导入成功！共" + sum + "条");
                return "show_flow";
            } else if(repeat+count==sum) {
                model.addAttribute("upload_success","流程单导入成功！共"+count+"条,重复"+repeat+"条");
                return "show_apply";
            }else {
                model.addAttribute("upload_error", "共有" + (sum - count) + "条导入失败！");
                return "show_flow";
            }
        }else {
            model.addAttribute("role", user.getRole());
            return "limit";
        }
    }


    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String Show(Model model,HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("User");
        logger.info("流程展示,name:{},role:{}",user.getName(),user.getRole());
        //权限验证
        if(user.getRole().equals("办单员")){
            model.addAttribute("role", user.getRole());
            return "limit";
        }else{
            try {
                List<Object> list=flowService.selectAll();
                if(list.isEmpty()){
                    model.addAttribute("show_error","请先导入流程表！" +
                            "<a href=\"/flow/manage\">流程管理</a>");
                }
                model.addAttribute("list", list);
                return "show_flow";
            }catch (Exception e){
                model.addAttribute("show_error","数据库读入失败！");
                return "show_flow";
            }
        }
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public String Query(@RequestParam("isSort")String isSort,
                        @RequestParam("flowResult")String flowResult,
                        @RequestParam("applyId")String applyId,
                        Model model,HttpServletRequest request){

        User user=(User) request.getSession().getAttribute("User");
        logger.info("流程查询,name:{},role:{}",user.getName(),user.getRole());
        List<Object> list = flowService.selectByDynamic(flowResult,applyId);
        //
        if(user.getRole().equals("办单员")){
            model.addAttribute("role", user.getRole());
            return "limit";
        }else{
            if (isSort.equals("0")) {
                model.addAttribute("list", list);
            } else if (isSort.equals("1")) {
                List<Flow> flows = new ArrayList<Flow>();
                for (Object object : list) {
                    Flow flow = (Flow) object;
                    flows.add(flow);
                }
                Collections.sort(flows);
                model.addAttribute("list", flows);
            }
        }
        return "show_flow";
    }

    @RequestMapping("/query/{applyId}")
    public String Query(@PathVariable("applyId")String id,Model model,HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("User");
        logger.info("role:{}applyid：{}",user.getRole(),id);
        if(!user.getRole().equals("主管")){
            model.addAttribute("error","没有此权限");
            model.addAttribute("list",null);
            return "detail_flow";
        }else{
            List<Object> list = flowService.selectByApplyId(id);
            if (list.isEmpty()){
                model.addAttribute("status","暂时没有此申请单的流程！");
            }
            model.addAttribute("list", list);
            return "detail_flow";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void Update(@RequestParam("applyid") String appluid,
                       @RequestParam("flowscale") String flowscale,
                       @RequestParam("id") String id){

        logger.info("applyid:{},flowscale:{}",appluid,flowscale);
        Flow flow=new Flow();
        flow.setId(Integer.valueOf(id));
        flow.setApplyid(appluid);
        try {
            flow.setFlowscale(Integer.valueOf(flowscale));
            flowService.updateByPrimaryKeySelective(flow);
        }catch (Exception e){
            e.printStackTrace();
        }

//        return "detail_flow";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String Delete(Model model){

        List<Object>list=flowService.selectAll();

        if(list.size()==0){
            logger.info("表已空。。。");
            model.addAttribute("delete_status","已清空，不要重复操作！");
            return "show_flow";
        }

        try {
            flowService.deleteAll();
        }catch (Exception e){
            model.addAttribute("delete_status","操作出错，稍后再试！");
            e.printStackTrace();
            logger.equals(e.getMessage());
            return "show_flow";
        }
        model.addAttribute("delete_status","成功，已清空！");
        return "show_flow";
    }

    @RequestMapping(value = "/result",method = RequestMethod.POST)
    public String Result(@RequestParam("applyid")String applyid,
                         Model model){

        logger.info("applyid:{}",applyid);
        List<Object> list=flowService.selectByApplyId(applyid);

        Apply myApply=applyService.selectByPrimaryKey(applyid);
        String result=Compute(list);

        Apply apply=new Apply();
        apply.setId(applyid);
        apply.setStatus("终审中");
        apply.setResult(myApply.getResult()+result);

        if(applyService.updateByPrimaryKeySelective(apply)==1){
            logger.info("更新成功！");
        }else {
            logger.info("更新失败！");
        }

        return "show_apply";
    }

    //根据三方权重计算审批结果
    public String Compute(List<Object> list){

        int sum=0;
        double flag=0.0;

        for (Object o:list){
            Flow flow=(Flow) o;
            sum=sum+flow.getFlowscale();
        }
        for (Object o:list){
            Flow flow=(Flow) o;
            try {
                if(flow.getFlowresult().equals("通过")){
                    flag=flag+(flow.getFlowscale()*1.0)/(sum*1.0);
                }else {
                    flag=flag-(flow.getFlowscale()*1.0)/(sum*1.0);
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.info("权重计算出错。");
            }

        }
        logger.info("flag:{}",flag);
        if (flag<0){
            return "二拒";
        }else if(flag>0){
            return "二过";
        }else{
            return "二无";
        }
    }

    public static void main(String[] args) {

    }
}
