package com.cj.example.springcloudgateway;

import cj.test.springcloud.examples.IuserAuthFeignClient;
import com.cj.springcloud.api.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@PropertySource(value = "classpath:loginfilter.properties")
@Component
public class AuthLoginGlobalFilter implements GlobalFilter,Ordered {

    @Value("#{'${jwt.ignoreUrls}'.split(',')}")
    List<String> ignoreUrls;

    @Autowired
    IuserAuthFeignClient userAuthFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest reques=exchange.getRequest();
//        reques.getU
        if(ignoreUrls!=null&&ignoreUrls.contains(reques.getURI().getPath())){
            return chain.filter(exchange);
        }
        String access_token=reques.getHeaders().getFirst("access_token");
        if(StringUtils.isBlank(access_token)){
            return onError(exchange,"尚未登陆");
        }
        R<String> r=userAuthFeignClient.validToken(access_token);
        if(r.getCode()==200){
            ServerHttpRequest shr=reques.mutate().header("uid",r.getData()).build();
           return chain.filter(exchange.mutate().request(shr).build());
        }

        return onError(exchange,r.getMsg());
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String msg){
        ServerHttpResponse response=exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Context-Type","application/json;charset=UTF-8");
        R rd=new R.Build<>().buildCustomize(msg,HttpStatus.UNAUTHORIZED.value());
        ObjectMapper objectMapper=new ObjectMapper();
        String rs="";
        try {
            rs=objectMapper.writeValueAsString(rd);
        } catch (JsonProcessingException e) {
            log.error("AuthLoginGlobalFilter.onError: {}",e.getMessage());
        }
        DataBuffer buffer=response.bufferFactory().wrap(rs.getBytes());
        return response.writeWith(Flux.just(buffer));
    }
}
