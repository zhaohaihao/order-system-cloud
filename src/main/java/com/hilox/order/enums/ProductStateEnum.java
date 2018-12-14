package com.hilox.order.enums;

import lombok.Getter;

/**
 * 商品状态枚举
 * Created by Hilox on 2018/12/14 0014.
 */
@Getter
public enum ProductStateEnum {

    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String message;

    ProductStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
