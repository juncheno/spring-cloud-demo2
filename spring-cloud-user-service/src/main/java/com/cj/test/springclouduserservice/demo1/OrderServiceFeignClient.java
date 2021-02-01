package com.cj.test.springclouduserservice.demo1;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("spring-cloud-order-service")
public interface OrderServiceFeignClient {

    @GetMapping("/orders")
    public String getAllOrder();
}
