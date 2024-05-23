package com.example.ems_thymeleaf.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Mvcconfig  implements WebMvcConfigurer {
//    通过这里面配置，不需要为每一个访问thymeleaf模板页面单独开发一个controller请求
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        viewcontroller:请求路径 viewname:跳转路径
        registry.addViewController("login").setViewName("login");

        registry.addViewController("register").setViewName("regist");

        registry.addViewController("addEmp").setViewName("addEmp");

        registry.addViewController("update").setViewName("updateEmp");
        registry.addViewController("mail").setViewName("mail");
        registry.addViewController("employeelogin").setViewName("employeelogin");
        registry.addViewController("attendance").setViewName("attendance");
        registry.addViewController("skilist").setViewName("skilist");
        registry.addViewController("skilogin").setViewName("skilogin");
        registry.addViewController("skiupdate").setViewName("skiupdate");
        registry.addViewController("skiadd").setViewName("skiadd");
        registry.addViewController("goodsadd").setViewName("goodsadd");
        registry.addViewController("goodslist").setViewName("goodslist");
        registry.addViewController("goodslogin").setViewName("goodslogin");
        registry.addViewController("goodsupdate").setViewName("goodsupdate");
        registry.addViewController("goodsorderlist").setViewName("goodsorderlist");
        registry.addViewController("goodsordered").setViewName("goodsordered");
        registry.addViewController("goodsorder").setViewName("goodsordered");
    }
}
