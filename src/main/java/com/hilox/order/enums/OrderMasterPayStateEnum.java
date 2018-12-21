package com.hilox.order.enums;

import lombok.Getter;

/**
 * 订单表支付状态枚举
 * Created by Hilox on 2018/12/20 0020.
 */
@Getter
public enum OrderMasterPayStateEnum {

    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    OrderMasterPayStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
