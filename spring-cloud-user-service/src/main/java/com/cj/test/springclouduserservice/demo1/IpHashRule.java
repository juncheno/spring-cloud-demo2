package com.cj.test.springclouduserservice.demo1;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IpHashRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;


    public IpHashRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }


    private int current;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }


    @Override
    public Server choose(Object key) {

        return choose(getLoadBalancer(),key);
    }

    public Server choose(ILoadBalancer lb,Object key) {
        if (lb == null) {
            System.out.println("no load balancer");
            return null;
        }
        List<Server> allServers = lb.getAllServers();

        int next=incrementAndGetModulo(2);
//        int current=0;
//        int next = (current + 1) % 2;
//        current=next;

        return allServers.get(next);
    }
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }

}
