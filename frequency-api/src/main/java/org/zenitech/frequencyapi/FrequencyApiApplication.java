package org.zenitech.frequencyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FrequencyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrequencyApiApplication.class, args);
    }
}
