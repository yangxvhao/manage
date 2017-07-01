package com.credit.controller;


import com.credit.model.Apply;
import com.credit.model.User;
import com.credit.service.IApplyService;
import com.credit.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @RequestMapping(value = "/manage",method = RequestMethod.GET)
    public String Manage(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("User");
        logger.info("申请单管理,name:{},role:{}",user.getName(),user.getRole());
        return "apply_manage";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String Manage(@RequestParam("excelFile") MultipartFile multipartFile,Model model){
        logger.info("文件上传.............");

        List<Object> list=new ArrayList<Object>();
        try {
            list= ExcelUtils.ExcelForList(multipartFile,Apply.class,false);
        }catch (Exception e){
            model.addAttribute("upload_error","上传出现错误，请验证格式是否正确！");
            e.getMessage();
            return "show_apply";
        }

        int sum=0;int count=0;int repeat=0;

        for (Object object:list){
            sum++;
            Apply apply=(Apply)object;
            if(applyService.selectByPrimaryKey(apply.getId())==(null)){
                //给一个初始值
                apply.setCheckmoney(0.0);
                apply.setResult("未审批");
                apply.setStatus("已提交");
                try {
                    if(applyService.insert(apply)==1){
                        count++;
                    }
                }catch (Exception e){
                    model.addAttribute("upload_error","数据库存入错误，请验证格式是否正确！");
                    e.getMessage();
                    return "show_apply";
                }
                logger.info(apply.toString());
            }else {
                repeat++;
                logger.info("repeat:{}",repeat);
            }
        }
        if(sum==count){
            model.addAttribute("upload_success","申请单导入成功！共"+sum+"条");
            return "show_apply";
        }else if(repeat+count==sum) {
            model.addAttribute("upload_success","申请单导入成功！共"+count+"条,重复"+repeat+"条");
            return "show_apply";
        }
         else {
            model.addAttribute("upload_error","共有"+(sum-count)+"条导入失败！");
            return "show_apply";
        }
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String Show(Model model,HttpServletRequest request){

        User user=(User) request.getSession().getAttribute("User");
        logger.info("申请单管理,name:{},role:{}",user.getName(),user.getRole());
        //展示办单员自己的申请单
        if(user.getRole().equals("办单员")){
            List<Object> list=applyService.selectByApplyMember(user.getName());
            if(list.isEmpty()){
                model.addAttribute("show_error","还未导入你的申请单，请导入" +
                        "<a href=\"/apply/manage\">申请单管理</a>");
            }
            model.addAttribute("list", list);
        }else if(user.getRole().equals("主管")){
            //如果是主管，展示全部申请单
            List<Object> list=applyService.selectAll();
            if(list.isEmpty()){
                model.addAttribute("show_error","还没有办单员导入申请单！");
            }
            model.addAttribute("list", list);
        }else if(user.getRole().equals("经理")){
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
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public String Query(@RequestParam("applyType")String type,
                        @RequestParam("applyTimeStart") String applyTimeStart,
                        @RequestParam("applyTimeEnd") String applyTimeEnd,
                        @RequestParam("applyMoneyMin") String applyMoneyMin,
                        @RequestParam("applyMoneyMax") String applyMoneyMax,
                        @RequestParam("status") String status,
                        @RequestParam("result") String result,
                        Model model,
                        HttpServletRequest request){

        User user=(User) request.getSession().getAttribute("User");
        logger.info("申请单查询name:{},role{},type:{},time:{},time:{},money:{},money:{},status:{},result:{}",
                user.getName(),user.getRole(),type,applyTimeStart,
                applyTimeEnd,applyMoneyMin,applyMoneyMax,status,result);

        List<Object> list=applyService.selectByDynamic(user.getRole(),user.getName(),type,
                applyTimeStart,applyTimeEnd, applyMoneyMin,applyMoneyMax,status,result);
        logger.info(list.toString());
        model.addAttribute("list",list);
        return "show_apply";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String Delete(Model model,HttpServletRequest request){

        User user=(User)request.getSession().getAttribute("User");

        List<Object> list = new ArrayList<Object>();
        if(user.getRole().equals("办单员")){
            logger.info("办单员删除自己的，");
            list=applyService.selectByApplyMember(user.getName());
        }
        else {
            logger.info("经理可以删除全部的，");
            list=applyService.selectAll();
        }
        if(list.size()==0){
            logger.info("表已空。。。");
            model.addAttribute("delete_status","已清空，不要重复操作！");
            return "show_apply";
        }

        try {
            if(user.getRole().equals("办单员")) {
                applyService.deleteByApplyMember(user.getName());
            }else {
                    applyService.deleteAll();
                }
        }catch (Exception e){
            model.addAttribute("delete_status","操作出错，稍后再试！");
            e.printStackTrace();
            logger.equals(e.getMessage());
            return "show_apply";
        }
        model.addAttribute("delete_status","成功，已清空！");
        return "show_apply";
    }

    @RequestMapping(value = "/check/{applyid}/{result}",method = RequestMethod.GET)
    public String Check(@PathVariable("applyid")String applyid,
                        @PathVariable("result")String result){

        logger.info("applyid:{},result:{}",applyid,result);

        if(result.equals("拒绝")){
            Apply apply=new Apply();
            apply.setId(applyid);
            apply.setResult("初拒");
            apply.setStatus("二级审批中");
            applyService.updateByPrimaryKeySelective(apply);
        }else {
            Apply apply=new Apply();
            apply.setId(applyid);
            apply.setResult("初过");
            apply.setStatus("二级审批中");
            applyService.updateByPrimaryKeySelective(apply);
        }
        return "show_apply";
    }

    @RequestMapping(value = "/check2/{applyid}/{result}",method = RequestMethod.GET)
    public String Check2(@PathVariable("applyid")String applyid,
                        @PathVariable("result")String result){

        logger.info("applyid:{},result:{}",applyid,result);

        Apply myApply=applyService.selectByPrimaryKey(applyid);
        if(result.equals("拒绝")){
            Apply apply=new Apply();
            apply.setId(applyid);
            apply.setResult(myApply.getResult()+"终拒");
            apply.setStatus("审批结束");
            applyService.updateByPrimaryKeySelective(apply);
        }else {
            Apply apply=new Apply();
            apply.setId(applyid);
            apply.setResult(myApply.getResult()+"终过");
            apply.setStatus("审批结束");
            applyService.updateByPrimaryKeySelective(apply);
        }
        Apply apply=applyService.selectByPrimaryKey(applyid);
        apply.setId(applyid);
        apply.setCheckmoney(Check2(apply));
        applyService.updateByPrimaryKeySelective(apply);
        return "show_apply";
    }

    public double Check2(Apply apply){

        double price=0.0;

        //根据审批等级给出不同审批金额
        //初10% 二 60% 终 30%
        final Map map=new HashMap(){
            {
                put("初过二过终过",1.0);
                put("初过二过终拒",0.7);
                put("初拒二过终过",0.9);
                put("初拒二过终拒",0.6);
                put("初过二拒终过",0.4);
                put("初过二拒终拒",0.1);
                put("初拒二拒终过",0.3);
                put("初拒二拒终拒",0.0);
                put("初过二无终过",0.4);
                put("初过二无终拒",0.1);
                put("初拒二无终过",0.3);
                put("初拒二无终拒",0.0);
            }
        };

        logger.info("applymoney:{},result:{}",apply.getApplymoney(),apply.getResult());
        try {
            price=apply.getApplymoney()*Double.valueOf(map.get(apply.getResult()).toString());
            DecimalFormat df=new DecimalFormat("#.00");
            price= Double.parseDouble(df.format(price));
        }catch (Exception e){
            e.printStackTrace();
        }


        logger.info("price:{}",price);
        return price;
    }

    public static void main(String[] args) {
        double price=5040.910000000001;
        DecimalFormat df=new DecimalFormat("#.00");
        price= Double.parseDouble(df.format(price));
        System.out.println(price);
    }

//    @RequestMapping(value = "/result",method = RequestMethod.POST)
//    public String Result(@RequestParam("applyid")String applyid,
//                         @RequestParam("result")String result,
//                         Model model){
//
//        logger.info("applyid:{},result:{}",applyid,result);
//
//        Apply apply=applyService.selectByPrimaryKey(applyid);
//
//        Apply applyCheck=new Apply();
//        applyCheck.setId(applyid);
//        if(apply.getStatus().contains("审批中")){
//            if(apply.getResult().equals("初审拒绝")){
//                logger.info("初审拒绝");
//                if(result.equals("拒绝")){
//
//                    applyCheck.setResult("终审拒绝");
//                    applyCheck.setStatus("审批结束");
//                    applyService.updateByPrimaryKeySelective(applyCheck);
//                }else if(result.equals("通过")){
//                    applyCheck.setResult("终审通过");
//                    applyCheck.setStatus("审批结束");
//                    //初审没通过，终审通过，审批金额为申请金额的80%
//                    applyCheck.setCheckmoney(apply.getApplymoney()*0.8);
//                    applyService.updateByPrimaryKeySelective(applyCheck);
//                }
//            }else if(apply.getResult().equals("初审通过")){
//                logger.info("初审通过");
//                if(result.equals("拒绝")){
//                    applyCheck.setResult("终审拒绝");
//                    applyCheck.setStatus("审批结束");
//                    //初审通过，终审没过，审批金额为申请金额的20%
//                    applyCheck.setCheckmoney(apply.getApplymoney()*0.2);
//                    applyService.updateByPrimaryKeySelective(applyCheck);
//                }else if(result.equals("通过")){
//                    applyCheck.setResult("终审通过");
//                    applyCheck.setStatus("审批结束");
//                    //初审通过，终审通过，审批金额为申请金额的100%
//                    applyCheck.setCheckmoney(apply.getApplymoney());
//
//                    logger.info("checkMoney:{}",applyCheck.toString());
//                    applyService.updateByPrimaryKeySelective(applyCheck);
//                }
//            }
//        }else {
//            model.addAttribute("check_status","初审还未进行！");
//        }
//
//        return "show_apply";
//    }
}
