package com.cj.test.springclouduserservice.demo3.hystrixController;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@DefaultProperties(defaultFallback = "fallbackDefault")
@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/hystrix/{num}")
    @HystrixCommand(commandProperties={
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value = "10000"),
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "5000")},
            fallbackMethod = "fallback",groupKey = "gkey",threadPoolKey ="order-service1" )
    public String testHystrix(@PathVariable("num")int num){
        if(num%2==0)
            return "调用成功";
        return restTemplate.getForObject("http://localhost:8083/cjtest",String.class);

    }
    @GetMapping("/hystrix/timeout/{num}")
    @HystrixCommand(commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")},
            fallbackMethod = "fallbackTimeout")
    public String testHystrixTimeout(@PathVariable("num")int num){
        if(num%2==0)
            return "调用成功";
        return restTemplate.getForObject("http://localhost:8083/cjtest",String.class);

    }

    public String fallback(int num){
        return "服务已被熔断";
    }
    public String fallbackTimeout(int num){
        return "系统繁忙";
    }
    public String fallbackDefault(){
        return "系统繁忙(类级别配置)";
    }
}
