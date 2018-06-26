package org.zenitech.frequencyconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class FrequencyConfigApplication {

    //TODO: Central config log level for all applications as ERROR

    public static void main(String[] args) {
        SpringApplication.run(FrequencyConfigApplication.class, args);
    }
}
