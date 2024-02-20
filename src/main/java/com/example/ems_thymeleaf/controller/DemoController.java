package com.example.ems_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("demo")
public class DemoController {
//    @RequestMapping("demo")
//    public String demo(Model model){
//        model.addAttribute("msg","hello");
//        return "demo";
//    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }
}


