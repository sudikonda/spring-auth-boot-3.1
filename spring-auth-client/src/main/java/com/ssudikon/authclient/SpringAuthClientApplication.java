package com.ssudikon.authclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAuthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthClientApplication.class, args);
    }

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(rs -> rs.path("/")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://localhost:8081"))
                .build();
    }

}
