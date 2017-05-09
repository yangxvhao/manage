package com.credit.controller;


import com.credit.model.Apply;
import com.credit.service.IApplyService;
import com.credit.utils.ExcelUtils;
import com.sun.javafx.sg.prism.NGShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date：08
 * @author:yangxvhao
 */
@Controller
@RequestMapping("apply")
public class ApplyController {

    private Logger logger= LoggerFactory.getLogger(ApplyController.class);

    @Autowired(required = false)
    IApplyService applyService;

    @RequestMapping(value = "/manage/{name}/{role}",method = RequestMethod.GET)
    public String Manage(@PathVariable("name")String name, @PathVariable("role")String role, Model model){
        logger.info("申请单管理,姓名,角色"+name+","+role);
        return "apply_manage";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String Manage(@RequestParam("excelFile") MultipartFile multipartFile,Model model){
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
            model.addAttribute("upload_success","申请单导入成功！共"+sum+"条");
            return "show_apply";
        }else {
            model.addAttribute("upload_error","共有"+(sum-count)+"条导入失败！");
            return "show_apply";
        }
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String Show(Model model){
        List<Object> list=applyService.selectAll();
        /*for (Object object:list) {
            Apply apply = (Apply) object;
            logger.info("show apply"+apply.toString());
            model.addAttribute("apply", apply);
        }*/
        model.addAttribute("list", list);
        return "show_apply";
    }
}
