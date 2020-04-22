package com.zhh.osc.order.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhh
 * @description
 * @date 2020-04-21 21:04
 */
public interface StreamClient {

    @Input("myMessage")
    SubscribableChannel input();

    @Output("myMessage")
    MessageChannel output();
}
