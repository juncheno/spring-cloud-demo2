package com.cj.test.springclouduserservice.demo1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignController {

    @Autowired
    OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("testfeign")
    public String test(){
        return orderServiceFeignClient.getAllOrder();
    }
}
