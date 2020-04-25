package com.zhh.osc.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 09:53
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 跨域配置
        final CorsConfiguration config = new CorsConfiguration();
        // 是否支持 Cookie 跨域
        config.setAllowCredentials(true);
        // 原始域
        config.setAllowedOrigins(Arrays.asList("*"));
        // 允许头
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setMaxAge(300L);

        // 将跨域配置注册到 source 中
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
