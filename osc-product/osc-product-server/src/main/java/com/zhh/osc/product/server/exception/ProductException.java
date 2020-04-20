package com.zhh.osc.product.server.exception;

import com.zhh.osc.product.server.enums.ResultEnum;

/**
 * @author zhh
 * @description
 * @date 2020-04-20 16:02
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
