package com.example.demo.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;
//保证依赖的组件始终是单实例的
@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet,"/my","/my02");

    }
    @Bean
    public FilterRegistrationBean myFilter(){
        MyFilter myFilter = new MyFilter();

        //return new FilterRegistrationBean(myFilter,myServlet());

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);

        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/my02","/my03"));

        return filterRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean myListener(){

        MyServletContextListener myServletContextListener = new MyServletContextListener();



        return new ServletListenerRegistrationBean(myServletContextListener);
    }


}
