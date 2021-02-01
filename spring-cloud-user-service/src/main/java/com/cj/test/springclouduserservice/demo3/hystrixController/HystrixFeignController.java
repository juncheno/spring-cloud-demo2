package com.cj.test.springclouduserservice.demo3.hystrixController;


import com.test.cj.examples.FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixFeignController {

    @Autowired
    private FeignClient feignClient;

    @GetMapping("/hystrix/feign")
    public String test(){
        System.out.println("开始查询order");
        return feignClient.test();
    }

    @GetMapping("/hystrix/feigndemo")
    public String test2(){
        System.out.println("开始插入order");
        return feignClient.test2();
    }
}
