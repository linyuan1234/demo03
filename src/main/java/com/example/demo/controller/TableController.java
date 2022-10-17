package com.example.demo.controller;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    @GetMapping("/basic_table")
    public String basic_table(){

        int i=10;
        i=i/0;
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){
        //表格内容的遍历
        List<User> users = Arrays.asList(new User("刘韦", "aab"), new User("杨子", "qqq"),
                new User("张三", "bbb"), new User("可可", "123"));
        model.addAttribute("users",users);

        return "table/dynamic_table";
    }
    @GetMapping("/editable_table")
    public String editable_table(){

        return "table/editable_table";
    }
    @GetMapping("/pricing_table")
    public String pricing_table(){

        return "table/pricing_table";
    }
    @GetMapping("/responsive_table")
    public String responsive_table(){

        return "table/responsive_table";
    }




}
