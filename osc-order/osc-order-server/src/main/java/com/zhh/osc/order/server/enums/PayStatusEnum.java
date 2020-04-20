package com.zhh.osc.order.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 16:08
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String message;
}
