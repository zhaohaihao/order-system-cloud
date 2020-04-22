package com.zhh.osc.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author zhh
 * @description
 * @date 2020-04-21 21:07
 */
@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    @StreamListener("myMessage")
    public void process1(String message) {
        log.info("StreamReceiver: {}", message);
    }

    @StreamListener("myMessage2")
    @SendTo("myMessage")
    public String process2(Object message) {
        log.info("StreamReceiver: {}", message);
        return "发送成功";
    }
}
