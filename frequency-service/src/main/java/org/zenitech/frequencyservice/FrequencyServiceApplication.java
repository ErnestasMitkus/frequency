package org.zenitech.frequencyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
public class FrequencyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrequencyServiceApplication.class, args);
    }
}
