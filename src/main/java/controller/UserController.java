package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @date：06
 * @author:fushuai
 */
@Controller
public class UserController {
   private static Logger logger=LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/health")
    @ResponseBody
    public String Hello (){
        logger.info("keep alive");
        return "keep alive";
    }
}
