package com.cj.examples.springcloudeurakaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurakaServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ca=SpringApplication.run(SpringCloudEurakaServerApplication.class, args);

//        System.out.println(ca.getBean(Chief.class));
//        Chief chief=ca.getBean(Chief.class);
//        System.out.println(chief.makeOneCake());
//        System.out.println(ca.getBean(Cake.class));
//        System.out.println(ca.getBean(Cake.class));

//        PicDao picDao=ca.getBean(PicDao.class);
//        PicDao picDao1=ca.getBean(PicDao.class);
//        System.out.println(picDao+"==="+picDao1);
//        picDao.print();
    }

}
