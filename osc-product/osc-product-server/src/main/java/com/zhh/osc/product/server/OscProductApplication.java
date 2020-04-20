package com.zhh.osc.product.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhh
 * @description 商品服务启动项
 * @date 2020-04-17 14:47
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OscProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(OscProductApplication.class, args);
    }

}
