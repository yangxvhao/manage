package com.credit.controller;

import com.credit.model.User;
import com.credit.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @date：06
 * @author:fushuai
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
    @RequestMapping("/index")
    public String Index(){
        return "index";
    }
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String Index(@ModelAttribute("user") User user){
        logger.info("---"+user.toString());
        if(userService.addUser(user)==1) {
            return "login";
        }else {
            return "error";
        }
    }
    @RequestMapping("/login")
    public String Login(){
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(@ModelAttribute("user") User user, Model model){
        logger.info(user.toString());
        logger.info(userService.getUserByName(user.getName()).toString());
        boolean isTrue= userService.getUserByName(user.getName()).getPassword().equals(user.getPassword());
        if(isTrue){
            model.addAttribute(user);
            return "success";
        }
        else {
            return "error";
        }
    }
}
