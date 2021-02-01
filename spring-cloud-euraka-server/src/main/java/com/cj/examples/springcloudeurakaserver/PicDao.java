package com.cj.examples.springcloudeurakaserver;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Scope;

import static com.cj.examples.springcloudeurakaserver.Genre.Color.BLACK;
import static com.cj.examples.springcloudeurakaserver.Genre.Color.RED;

@Named
public class PicDao {

    public PicDao(){
        System.out.println("----PicDao construct-----");
    }

    @PostConstruct
    public void init(){
        System.out.println("----PicDao init-----");
    }

    @Inject
    @Genre(color=BLACK)
    private Mpic mpic;

    public  void print(){
        System.out.println("PicDao "+mpic.see());
    }


}
