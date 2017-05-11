package com.credit.controller;

import com.credit.model.User;
import com.credit.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @date：06
 * @author:yangxvhao
 */
@Controller
public class UserController {
    private static Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired(required = false)
    private IUserService userService;

    @RequestMapping("/health")
    @ResponseBody
    public String Health (){
        logger.info("keep alive");
        return "keep alive";
    }
    @RequestMapping(value = {"/index","/"})
    public String Index(){
        return "index";
    }
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String Index(@ModelAttribute("user") User user,Model model){
        logger.info("---"+user.toString());
        User user1=userService.getUserByName(user.getName());

        if(user1==null) {
            if (userService.addUser(user) == 1) {
                return "login";
            } else {
                return "error";
            }
        }else {
            logger.info(user1.toString());
            model.addAttribute("error","用户已存在，请登录");
            return "login";
        }
    }
    @RequestMapping("/login")
    public String Login(){
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(@ModelAttribute("user") User user, Model model){
        logger.info(user.toString()+"登录");
        User user1=userService.getUserByName(user.getName());
        logger.info("数据库信息："+user1.toString());
        boolean isTrue=user1.getPassword().equals(user.getPassword())&&user1.getRole().equals(user.getRole());
        if(isTrue){
            model.addAttribute(user);
            return "success";
        }
        else {
            model.addAttribute("error","用户名、密码或角色错误！请确认登录信息");
            return "login";
        }
    }
    @RequestMapping(value = "/changePwd/{name}/{role}",method = RequestMethod.GET)
    public String ChangPwd(@PathVariable("name") String name,@PathVariable("role") String role,Model model) {
        logger.info("登陆用户名："+name+role);
        model.addAttribute(name);
        model.addAttribute(role);
        return "change_pwd";
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    public String ChangPwd(@ModelAttribute("user") User user,Model model){

        User user1=userService.getUserByName(user.getName());
        user.setId(user1.getId());
        logger.info(user.toString()+"修改密码");
        logger.info("数据库信息："+user1.toString());
        if(userService.updateByPrimaryKeySelective(user)==1) {
            model.addAttribute("change_succes","密码修改成功！请登录");
            return "login";
        }else
        {
            model.addAttribute("change_error","密码修改失败，请重试！");
            return "change_pwd";
        }
    }
    @RequestMapping(value = "/root/{name}/{role}",method = RequestMethod.GET)
    public String Root(@PathVariable("name")String name,
                       @PathVariable("role")String role,
                       Model model){

        logger.info("返回主页，姓名：{},角色：{}",name,role);
        User user=new User();
        user.setName(name);
        user.setRole(role);
        model.addAttribute("user",user);
        return "success";
    }

}
