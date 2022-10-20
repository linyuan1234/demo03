package com.example.demo.controller;

import com.example.demo.pojo.Account;
import com.example.demo.pojo.User;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AccountService accountService;
@GetMapping("/acct")
@ResponseBody
    public Account getById(@RequestParam("id") Integer id){

        return accountService.getAcctByid(id);
    }

    @GetMapping("/sql")
    @ResponseBody
    public String queryFromDb(){
        Integer integer = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);


        return  integer.toString();
    }
/*
* 登录页面
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
        //Object loginUser = session.getAttribute("loginUser");
       /* if (loginUser != null) {
                return "redirect:/main";
            } else {
                model.addAttribute("msg", "请重新登录！");
                return "login";
            }*/
        return "redirect:/main";


    }
    @GetMapping("/main")
    public String mainPage(HttpSession session,Model model){
        //是否登录   拦截器 过滤器
       /* Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            return "index";
        }else {
            model.addAttribute("msg","请重新登录");
            return "login";
        }*/
      //  log.info("执行的方法是{}","mainPage");
        return "index";
    }
}
