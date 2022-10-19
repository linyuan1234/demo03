package com.example.demo.interceptor;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
/*
* 登录检查
* 1.配置好拦截器要拦截哪些请求
* 2.把这些配置放在容器中
*
* 拦截器原理
* 1.根据当前请求，找到HandlerExecutionChain[可以处理请求的handler以及handler的所有拦截器]
* 2.先来顺序执行所有拦截器的preHandle方法
* 2.1如果当前拦截器的preHandler返回为true，则执行下一个拦截器的preHandle.如果当前拦截器返回为false。直接倒叙执行所有已经执行了的拦截器的afterCompletion
* 3.如果任何一个拦截器执行失败，直接跳出不执行目标方法
* 4.所有拦截器都返回true，执行目标方法
* 5.倒叙执行所有拦截器的postHandle
* 6.前面的任何步骤有异常都会直接触发afterCompletion
*
*
*
* */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录检查
        //log.info("拦截的请求路径是{}",request.getRequestURI());
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            //放行
            return true;
        }
        //拦截

        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
