package com.zhh.osc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhh
 * @description
 * @date 2020-04-20 17:23
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigServer
public class OscConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(OscConfigApplication.class, args);
    }
}
