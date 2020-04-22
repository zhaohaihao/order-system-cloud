package com.zhh.osc.order.server.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhh
 * @description 发送mq消息
 * @date 2020-04-21 20:40
 */
@Component
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now" + new Date());
    }
}
