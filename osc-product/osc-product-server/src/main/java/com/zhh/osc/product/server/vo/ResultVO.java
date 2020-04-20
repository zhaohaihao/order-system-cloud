package com.zhh.osc.product.server.vo;

import lombok.Data;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 15:44
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
