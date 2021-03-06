package org.zenitech.frequencygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class FrequencyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrequencyGatewayApplication.class, args);
    }
}
