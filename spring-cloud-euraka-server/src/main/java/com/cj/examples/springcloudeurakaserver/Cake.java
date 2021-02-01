package com.cj.examples.springcloudeurakaserver;

import org.springframework.context.annotation.Bean;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

//
@Singleton
@Named
public class Cake {

//    @Inject
    public Cake(){

    }

    private String name="cake name";

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
