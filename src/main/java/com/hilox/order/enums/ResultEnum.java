package com.hilox.order.enums;

import lombok.Getter;

/**
 * 返回消息枚举
 * Created by Hilox on 2018/12/21 0021.
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不足"),
    ;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;
}
