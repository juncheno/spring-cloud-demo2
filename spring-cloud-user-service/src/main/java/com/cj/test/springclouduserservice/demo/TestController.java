package com.cj.test.springclouduserservice.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

    @Qualifier
    @Autowired
    private List<TestClass> testClasss=Collections.emptyList();

    @GetMapping("/test")
    public Object test(){
        return "1233";
    }
}
