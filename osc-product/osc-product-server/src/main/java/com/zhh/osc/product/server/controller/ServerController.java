package com.zhh.osc.product.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 16:32
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product' msg";
    }
}
