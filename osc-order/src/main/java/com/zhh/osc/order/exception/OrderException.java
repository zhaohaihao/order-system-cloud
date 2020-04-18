package com.zhh.osc.order.exception;

import com.zhh.osc.order.enums.ResultEnum;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 16:18
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
