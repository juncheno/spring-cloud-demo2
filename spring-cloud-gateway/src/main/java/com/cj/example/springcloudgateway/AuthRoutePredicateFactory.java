package com.cj.example.springcloudgateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    public AuthRoutePredicateFactory() {
        super(Config.class);
    }

    public static final String NAME="name";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {


        return new GatewayPredicate(){
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {

                HttpHeaders headers =serverWebExchange.getRequest().getHeaders();
                List<String> list=headers.get(config.getName());
                if (list.size()>0)
                    return true;

                return false;
            }
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
