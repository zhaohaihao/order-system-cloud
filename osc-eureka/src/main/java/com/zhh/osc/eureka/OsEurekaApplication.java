package com.zhh.osc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhh
 * @description Eureka注册中心启动项
 * @date 2020-04-17 14:47
 */
@SpringBootApplication
@EnableEurekaServer
public class OsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsEurekaApplication.class, args);
    }

}
