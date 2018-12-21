package com.hilox.order.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by Hilox on 2018/12/19 0019.
 */
@Data
public class ResultVO<T> {

    /** 状态码 **/
    private Integer code;

    /** 提示信息 **/
    private String msg;

    /** 返回数据 **/
    private T data;
}
