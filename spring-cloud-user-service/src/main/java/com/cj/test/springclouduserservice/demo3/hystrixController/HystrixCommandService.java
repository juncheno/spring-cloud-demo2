package com.cj.test.springclouduserservice.demo3.hystrixController;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

public class HystrixCommandService extends HystrixCommand<String> {

    private int num;
    private RestTemplate restTemplate;



    protected HystrixCommandService(int num, RestTemplate restTemplate) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("order-service"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
        .withCircuitBreakerRequestVolumeThreshold(5)));
        this.num=num;
        this.restTemplate=restTemplate;
    }

    @Override
    protected String run() throws Exception {
        if(num%2==0)
            return "正常访问";
        return restTemplate.getForObject("http://localhost:8083/cjtest",String.class);
    }

    @Override
    protected String getFallback() {
        return "请求被降级";
    }
}
