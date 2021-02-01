package com.cj.examples.springcloudeurakaserver;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

@Named
public class Chief {

    private String name;


//    @Inject
    private Cake cake;

    @Inject
    public Chief(Cake cake) {
        this.cake = cake;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cake makeOneCake() {
        System.out.println(getName() + " make " + cake.getName());
        return cake;
    }
}
