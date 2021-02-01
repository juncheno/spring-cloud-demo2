package com.cj.test.springclouduserservice.demo3.hystrixController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixCommandController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hystrix/command/{num}")
    public String hystrixCommand(@PathVariable("num") int num){
        HystrixCommandService hystrixCommandService=new HystrixCommandService(num,restTemplate);
        if(num%2==0)
            return "正常请求";

        return hystrixCommandService.execute();
    }
}
