package com.zhh.osc.order.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.zhh.osc.product.client")
public class OscOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OscOrderApplication.class, args);
    }

}
