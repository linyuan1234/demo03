package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
/*
* 来登录页
* */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }
    //重定向，防止表单重复提交
    @PostMapping("/index")
    public String main(User user, HttpSession session, Model model){
        if (!StringUtils.isEmpty(user.getUsername())&&!StringUtils.isEmpty(user.getPassword())){
            session.setAttribute("loginUser",user);
            return "redirect:/main";
        }else {
            model.addAttribute("msg","账号密码错误");
            return "login";
        }

    }

    //重新回到main页面/index页面
    @GetMapping("/index")
    public String main2( HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
                return "redirect:/main";
            } else {
                model.addAttribute("msg", "请重新登录！");
                return "login";
            }



    }
    @GetMapping("/main")
    public String mainPage(HttpSession session,Model model){
        //是否登录   拦截器 过滤器
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            return "index";
        }else {
            model.addAttribute("msg","请重新登录");
            return "login";
        }
    }
}
