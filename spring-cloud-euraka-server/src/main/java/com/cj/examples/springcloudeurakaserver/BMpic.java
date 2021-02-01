package com.cj.examples.springcloudeurakaserver;

import javax.inject.Named;

@Named
@Genre(color = Genre.Color.BLACK)
public class BMpic implements Mpic {


    @Override
    public String see() {
        return "black";
    }
}
