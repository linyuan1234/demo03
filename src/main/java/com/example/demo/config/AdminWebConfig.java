package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
/*
*
* 添加拦截器
*
* 1.编写一个拦截器实现HandlerInterceptor接口
* 2.拦截器注册到容器中(实现WebMvcConfigurer的)
*3.指定拦截规则[如果是拦截所有，静态资源也会被拦截]
* */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//所有请求都被拦截，包括静态资源
                .excludePathPatterns("/","/index","/css/**","/js/**","/images/**","/fonts/**");
    }
}
