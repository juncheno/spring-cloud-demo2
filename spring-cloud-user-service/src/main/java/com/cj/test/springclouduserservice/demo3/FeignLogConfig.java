package com.cj.test.springclouduserservice.demo3;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLogConfig {

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
