package com.cj.test.springclouduserservice.demo3;

import com.cj.test.springclouduserservice.demo2.CjEnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.cj.test.springclouduserservice.demo3","com.test.cj.examples",
        "com.cj.test.springclouduserservice.demo3.hystrix","com.cj.test.springclouduserservice.demo3.feign"})
@EnableFeignClients("com.test.cj.examples")
@CjEnableFeignClients
@EnableHystrix
public class SpringCloudUserServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ca=
        SpringApplication.run(SpringCloudUserServiceApplication.class, args);

//        System.out.println(ca.getBean(HelloSerivce.class));
    }

}
