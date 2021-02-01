package com.cj.test.springclouduserservice.demo1;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

import java.util.Date;

public class MyPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        System.out.println("isAlive cur date"+new Date()+" "+server.getHost()+":"+server.getPort());
        return true;
    }
}
