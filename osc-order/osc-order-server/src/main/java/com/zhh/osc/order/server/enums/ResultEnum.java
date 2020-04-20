package com.zhh.osc.order.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 16:19
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXIST(3, "订单不存在"),
    ORDER_STATUS_ERROR(4, "订单状态错误"),
    ORDER_DETAIL_NOT_EXIST(5, "订单详情不存在"),

    ;

    private Integer code;

    private String message;
}
