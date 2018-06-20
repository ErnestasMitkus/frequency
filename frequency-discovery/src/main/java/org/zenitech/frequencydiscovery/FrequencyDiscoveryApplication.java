package org.zenitech.frequencydiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FrequencyDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrequencyDiscoveryApplication.class, args);
    }
}
