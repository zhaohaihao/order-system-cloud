package com.zhh.osc.order.server.controller;

import com.zhh.osc.order.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhh
 * @description
 * @date 2020-04-21 21:16
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        streamClient.output().send(MessageBuilder.withPayload("now" + new Date()).build());
    }
}
