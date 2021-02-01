package com.cj.test.springclouduserservice.demo3.hystrix;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CjHystrixCommand {

    String fallback() default "";

    int timeout() default 1000;

}
