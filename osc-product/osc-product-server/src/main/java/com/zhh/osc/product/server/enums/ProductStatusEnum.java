package com.zhh.osc.product.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhh
 * @description 商品上下架状态枚举
 * @date 2020-04-17 15:37
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String msg;
}
