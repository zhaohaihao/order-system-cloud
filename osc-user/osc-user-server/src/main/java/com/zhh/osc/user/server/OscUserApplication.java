package com.zhh.osc.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OscUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(OscUserApplication.class, args);
    }

}
