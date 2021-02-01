package com.cj.example.springcloudgateway;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


//@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    public static final String GATEWAY_ROUTE_KEY="gateway_dynamic_route";

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> list=new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTE_KEY).stream().forEach((route)->
        {list.add(JSON.parseObject(route.toString(), RouteDefinition.class));});

        return Flux.fromIterable(list);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition->{
            redisTemplate.opsForHash().put(GATEWAY_ROUTE_KEY,routeDefinition.getId(),JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });

    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id->{
            if(redisTemplate.opsForHash().hasKey(GATEWAY_ROUTE_KEY,id)) {
                redisTemplate.opsForHash().delete(GATEWAY_ROUTE_KEY, id);
                return Mono.empty();
            }
            return Mono.defer(()->
                Mono.error(new Exception("routeDefinition not found:"+routeId)));
        });
    }
}
