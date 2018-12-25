package com.hilox.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 微信Controller(原始)
 * Created by Hilox on 2018/12/25 0025.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法...");
        log.info("code={}", code);

        String url = "获取openid的url";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        log.info("result={}", result);
    }
}
