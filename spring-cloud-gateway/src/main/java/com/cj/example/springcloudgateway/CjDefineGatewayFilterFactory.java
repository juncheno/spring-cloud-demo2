package com.cj.example.springcloudgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class CjDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<CjDefineGatewayFilterFactory.Config> {
    private Logger logger=LoggerFactory.getLogger(CjDefineGatewayFilterFactory.class);

    public CjDefineGatewayFilterFactory() {
        super(Config.class);
    }

    public static final String NAME_KEY="name";
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
            logger.info("[pre] filter, name "+config.getName());
            //TODO
            return chain.filter(exchange).then(Mono.fromRunnable(
                    ()->{
                        //TODO
                        logger.info("[post]: Response filter");
                    }
            ));
        });
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
