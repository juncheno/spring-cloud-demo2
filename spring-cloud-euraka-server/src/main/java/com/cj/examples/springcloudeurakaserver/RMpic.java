package com.cj.examples.springcloudeurakaserver;


import javax.inject.Named;

import static com.cj.examples.springcloudeurakaserver.Genre.Color.RED;

@Named
@Genre(color = RED)
public class RMpic implements Mpic {
    @Override
    public String see() {
        return "red";
    }
}
