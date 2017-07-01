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

import javax.servlet.http.HttpServletRequest;


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
    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
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
    @RequestMapping(value = {"/login","/"})
    public String Login(){
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(@ModelAttribute("user") User user, Model model, HttpServletRequest request){
        logger.info(user.toString()+"登录");
        User user1=userService.getUserByName(user.getName());
        if(user1==null)
        {
            model.addAttribute("error","未注册，请注册");
            return "index";
        }
        logger.info("数据库信息："+user1.toString());
        boolean isTrue=user1.getPassword().equals(user.getPassword())&&user1.getRole().equals(user.getRole());
        if(isTrue){
            //在登录的时候设置session，用于后续判断
            request.getSession().setAttribute("User",user);
            request.getSession().setAttribute("name",user.getName());
            request.getSession().setMaxInactiveInterval(30*60);
            return "apply_manage";
        }
        else {
            model.addAttribute("error","用户名、密码或角色错误！请确认登录信息");
            return "login";
        }
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.GET)
    public String ChangPwd(Model model,HttpServletRequest request) {
        User user=(User)request.getSession().getAttribute("User");
        logger.info("session:name:{},pwd:{}",user.getName(),user.getPassword());
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
    @RequestMapping(value = "/forgetPwd",method = RequestMethod.GET)
    public String ForgetPwd(){
        return "forget_pwd";
    }

    @RequestMapping(value = "/forgetPwd",method = RequestMethod.POST)
    public String ForgetPwd(@ModelAttribute("user")User user,Model model){
        User user1=userService.getUserByName(user.getName());
        logger.info("忘记密码：");
        if(user1==null){
            model.addAttribute("forget","用户不存在，请确认用户名！");
        }else {
            logger.info("忘记密码：{}",user1.toString());
            user.setId(user1.getId());
            if(userService.updateByPrimaryKeySelective(user)==1) {
                model.addAttribute("forget", "密码重置成功！请登录");
            }else {
                model.addAttribute("forget", "系统繁忙，请重试！");
            }
        }
        return "forget_pwd";
    }

    @RequestMapping(value = "/root",method = RequestMethod.GET)
    public String Root(Model model ,HttpServletRequest request){

        User user=(User)request.getSession().getAttribute("User");
        logger.info("session:name:{},role:{}",user.getName(),user.getRole());
        return "apply_manage";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String Root(HttpServletRequest request){

        request.getSession().removeAttribute("User");
        request.getSession().removeAttribute("name");
        logger.info("remove session.");
        return "login";
    }
}
