package com.cj.test.springclouduserservice.demo2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class CjImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

       BeanDefinition beanDefinition =new GenericBeanDefinition();
       beanDefinition.setBeanClassName(HelloSerivce.class.getName());

       registry.registerBeanDefinition("helloService",beanDefinition);
    }
}
