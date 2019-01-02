package com.hilox.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动项
 * Created by Hilox on 2018/12/6 0006.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hilox.order.mapper")
public class HiloxOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiloxOrderApplication.class, args);
	}
}
