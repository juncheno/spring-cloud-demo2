package com.cj.test.springclouduserservice.demo3.hystrixController;


import com.cj.test.springclouduserservice.demo3.hystrix.CjHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CjHystrixController {

    @Autowired
    RestTemplate restTemplate;


    @CjHystrixCommand(fallback = "fallback",timeout = 5000)
    @GetMapping("/hystrix/test")
    public String test(){
        System.out.println("==========s");
        return restTemplate.getForObject("http://localhost:8083/cjtest",String.class);
    }

    public String fallback(){
        System.out.println("请求被降级");
        return "请求被降级";
    }
}
