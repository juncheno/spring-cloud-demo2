package com.cj.test.springclouduserservice.demo3.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
public class ConfigController {

    @Value("${env}")
    private String txt;


    @GetMapping("/user/config")
    public String config(){
        return txt;
    }
}
