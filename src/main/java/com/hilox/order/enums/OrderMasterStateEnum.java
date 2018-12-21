package com.hilox.order.enums;

import lombok.Getter;

/**
 * 订单表状态枚举
 * Created by Hilox on 2018/12/20 0020.
 */
@Getter
public enum OrderMasterStateEnum {

    NEW(0, "新订单"),
    FINISHED(1, "已完成"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderMasterStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
