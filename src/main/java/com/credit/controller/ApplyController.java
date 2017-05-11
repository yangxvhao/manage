package com.credit.controller;


import com.credit.model.Apply;
import com.credit.model.User;
import com.credit.service.IApplyService;
import com.credit.utils.ExcelUtils;
import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date：08
 * @author:yangxvhao
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {

    private Logger logger= LoggerFactory.getLogger(ApplyController.class);

    @Autowired(required = false)
    IApplyService applyService;

    @RequestMapping(value = "/success/{name}/{role}",method = RequestMethod.GET)
    public String Success(@PathVariable("name") String name, @PathVariable("role")String role, Model model){
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        return "success";
    }


    @RequestMapping(value = "/manage/{name}/{role}",method = RequestMethod.GET)
    public String Manage(@PathVariable("name")String name, @PathVariable("role")String role, Model model){
        logger.info("申请单管理,姓名,角色"+name+","+role);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        return "apply_manage";
    }

    @RequestMapping(value = "/upload/{name}/{role}",method = RequestMethod.POST)
    public String Manage(@PathVariable("name")String name,@PathVariable("role")String role,
                         @RequestParam("excelFile") MultipartFile multipartFile,Model model){
        logger.info(multipartFile.toString());
        List<Object> list= ExcelUtils.ExcelForList(multipartFile,Apply.class,false);
        int sum=0;int count=0;
        for (Object object:list){
            sum++;
            Apply apply=(Apply)object;
            if(applyService.insert(apply)==1){
                count++;
            }
            logger.info(apply.toString());
        }
        if(sum==count){
            model.addAttribute("name",name);
            model.addAttribute("role",role);
            model.addAttribute("upload_success","申请单导入成功！共"+sum+"条");
            return "show_apply";
        }else {
            model.addAttribute("name",name);
            model.addAttribute("role",role);
            model.addAttribute("upload_error","共有"+(sum-count)+"条导入失败！");
            return "show_apply";
        }
    }

    @RequestMapping(value = "/show/{name}/{role}",method = RequestMethod.GET)
    public String Show(@PathVariable("name")String name,@PathVariable("role")String role, Model model){

        logger.info("申请单展示，姓名，角色"+name+","+role);
        //展示办单员自己的申请单
        if(role.equals("办单员")){
            List<Object> list=applyService.selectByApplyMember(name);
            if(list.isEmpty()){
                model.addAttribute("show_error","还未导入你的申请单，请导入" +
                        "<a href=\"/apply/manage/${name}/${role}\">申请单管理</a>");
            }
            model.addAttribute("list", list);
        }else if(role.equals("经理")){
            //如果是经理，展示全部申请单
            List<Object> list=applyService.selectAll();
            if(list.isEmpty()){
                model.addAttribute("show_error","还没有办单员导入申请单！");
            }
            model.addAttribute("list", list);
        }else {
            model.addAttribute("show_error","参数传入错误");
            model.addAttribute("list", null);
        }

        return "show_apply";
    }
    @RequestMapping(value = "/query/{name}/{role}",method = RequestMethod.POST)
    public String Query(@PathVariable("name") String name,
                        @PathVariable("role") String role,
                        @RequestParam("applyType")String type,
                        @RequestParam("applyTimeStart") String applyTimeStart,
                        @RequestParam("applyTimeEnd") String applyTimeEnd,
                        @RequestParam("applyMoneyMin") String applyMoneyMin,
                        @RequestParam("applyMoneyMax") String applyMoneyMax,
                        Model model){

        logger.info("name:{},role{},type:{},time:{},time:{},money:{},money:{}",name,role,type,applyTimeStart,
                applyTimeEnd,applyMoneyMin,applyMoneyMax);
//        List<Object> list=null;
//        if(role.equals("办单员")){
//            list=applyService.selectByApplyMember(name);
//            if(list.isEmpty()){
//                model.addAttribute("show_error","还未导入你的申请单，请导入" +
//                        "<a href=\"/apply/manage/${name}/${role}\">申请单管理</a>");
//            }
//            model.addAttribute("list", list);
//        }else if(role.equals("经理")){
//            //如果是经理，展示全部申请单
//            list=applyService.selectAll();
//            for (Object object:list){
//                Apply apply=(Apply) object;
//                logger.info(apply.getApplydate());
//            }
//
//            if(list.isEmpty()){
//                model.addAttribute("show_error","还没有办单员导入申请单！");
//            }
//            model.addAttribute("list", list);
//        }else {
//            model.addAttribute("show_error","参数传入错误");
//            model.addAttribute("list", null);
//        }
        List<Object> list=applyService.selectByDynamic(role,name,type,applyTimeStart,applyTimeEnd,
                applyMoneyMin,applyMoneyMax);
        logger.info(list.toString());
        model.addAttribute("list",list);
        return "show_apply";
    }

}
