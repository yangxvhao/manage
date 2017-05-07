package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.IUserService;
import service.impl.IUserServiceImpl;

import javax.annotation.Resource;


/**
 * @date：06
 * @author:fushuai
 */
@Controller
public class UserController {
    private static Logger logger=LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;


    @RequestMapping("/health")
    @ResponseBody
    public String Hello (){
        logger.info("keep alive");
        return "keep alive";
    }
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String Index(@ModelAttribute("user") User user){
        user.setId(1);
        logger.info("---"+user.toString());
        userService.addUser(user);
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(@ModelAttribute("user") User user, Model model){
        logger.info(user.toString());
        model.addAttribute(user);
        return "success";
    }
}
