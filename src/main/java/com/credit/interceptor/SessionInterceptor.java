package com.credit.interceptor;

import com.credit.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date：2017/5/12
 * @author:yangxvhao
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private Logger logger= LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user=(User) request.getSession().getAttribute("User");

        if(user==(null)){
            response.setCharacterEncoding("UTF-8");
            logger.info("Interceptor：跳转到nologin页面！");
            response.sendRedirect("/nologin.jsp");
            return false;
        }else{
            logger.info("url:{},interceptor:{}",request.getRequestURI(),user.toString());
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
