package org.zenitech.frequencygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@EnableDiscoveryClient
@SpringBootApplication
public class FrequencyGatewayApplication {

    @Bean
    public RouteLocator custorRouteLocation(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.stripPrefix(1))
                        .uri("lb://frequency-api"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(FrequencyGatewayApplication.class, args);
    }
}
