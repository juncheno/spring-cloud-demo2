package com.cj.test.springclouduserservice.demo3;


import com.test.cj.examples.FeignClient;
import com.test.cj.examples.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    private FeignClient feignClient;

    @GetMapping("test1")
    public String test(){
        System.out.println("oder");
       return feignClient.test();
    }

}
