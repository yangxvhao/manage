package com.credit.controller;

import com.credit.model.Flow;
import com.credit.model.User;
import com.credit.service.IFlowService;
import com.credit.utils.ExcelUtils;
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

    @RequestMapping(value = "/manage/{name}/{role}",method = RequestMethod.GET)
    public String Upload(@PathVariable("name")String name,
            @PathVariable("role")String role,
                         Model model){
        logger.info("流程判断role:{}",role);
        if(role.equals("办单员")) {
            User user=new User();
            user.setName(name);
            user.setRole(role);
            model.addAttribute(user);
            model.addAttribute("flow_error", "您是办单员，暂无权限！");
            return "success";
        }else {
            model.addAttribute("name",name);
            model.addAttribute("role",role);
            return "flow_manage";
        }

    }

    @RequestMapping(value = "/upload/{name}/{role}",method = RequestMethod.POST)
    public String Manage(@PathVariable("name")String name,
                         @PathVariable("role")String role,
                         @RequestParam("excelFile") MultipartFile multipartFile,
                         Model model) {

        logger.info(multipartFile.toString());
        if (role.equals("经理")) {
            List<Object> list = ExcelUtils.ExcelForList(multipartFile, Flow.class, false);
            int sum = 0;
            int count = 0;
            for (Object object : list) {
                sum++;
                Flow flow = (Flow) object;
                if (flowService.insert(flow) == 1) {
                    count++;
                }
                logger.info(flow.toString());
            }
            if (sum == count) {
                model.addAttribute("name",name);
                model.addAttribute("role", role);
                model.addAttribute("upload_success", "流程单导入成功！共" + sum + "条");
                return "show_flow";
            } else {
                model.addAttribute("name",name);
                model.addAttribute("role", role);
                model.addAttribute("upload_error", "共有" + (sum - count) + "条导入失败！");
                return "show_flow";
            }
        }else {
            model.addAttribute("role", role);
            return "limit";
        }
    }


    @RequestMapping(value = "/show/{name}/{role}",method = RequestMethod.GET)
    public String Show(@PathVariable("name")String name,
                       @PathVariable("role")String role,
                       Model model){

        logger.info("流程单展示，姓名，角色"+name+","+role);
        //权限验证
        if(role.equals("办单员")){
            model.addAttribute("role", role);
            return "limit";
        }else if(role.equals("经理")){
            List<Object> list=flowService.selectAll();
            if(list.isEmpty()){
                model.addAttribute("show_error","数据库读入失败！");
            }
            model.addAttribute("list", list);
        }else {
            model.addAttribute("show_error","参数传入错误");
            model.addAttribute("list", null);
        }
        return "show_flow";
    }

    @RequestMapping(value = "/query/{name}/{role}",method = RequestMethod.POST)
    public String Query(@PathVariable("name")String name,
                        @PathVariable("role")String role,
                        @RequestParam("isSort")String isSort,
                        @RequestParam("flowResult")String flowResult,
                        @RequestParam("applyId")String applyId,
                        Model model){

        List<Object> list = flowService.selectByDynamic(flowResult,applyId);
        //
        if(isSort.equals("0")){
            model.addAttribute("list",list);
        }
        else if(isSort.equals("1")){
            List<Flow> flows=new ArrayList<Flow>();
            for (Object object:list){
                Flow flow=(Flow) object;
                flows.add(flow);
            }
            Collections.sort(flows);
            model.addAttribute("list",flows);
        }else {

        }
        model.addAttribute("name",name);
        model.addAttribute("role", role);
        return "show_flow";
    }

    @RequestMapping("/query/{role}/{applyId}")
    public String Query(@PathVariable("role")String role,@PathVariable("applyId")String id,Model model){
        logger.info("role:{}applyid：{}",role,id);
        if(role.equals("办单员")){
            model.addAttribute("error","办单员没有此权限");
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
}
