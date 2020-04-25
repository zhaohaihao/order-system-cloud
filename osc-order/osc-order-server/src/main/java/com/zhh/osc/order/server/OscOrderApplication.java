package com.zhh.osc.order.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.zhh.osc.product.client")
@ComponentScan(basePackages = "com.zhh.osc")
@EnableHystrixDashboard
public class OscOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OscOrderApplication.class, args);
    }

}
