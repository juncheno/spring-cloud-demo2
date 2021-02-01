package com.cj.test.springclouduserservice.demo2;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CjImportBeanDefinitionRegistrar.class)
public @interface CjEnableFeignClients {
}
