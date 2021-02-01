package com.cj.test.springclouduserservice.demo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Qualifier
    @Bean
    public TestClass testClass1(){
        return new TestClass("testClass1");
    }

    @Qualifier("s2")
    @Bean
    public TestClass testClass2(){
        return new TestClass("testClass2");
    }
}
