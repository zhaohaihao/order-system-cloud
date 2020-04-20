package com.zhh.osc.product.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhh
 * @description
 * @date 2020-04-20 16:03
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;

    private String message;
}
