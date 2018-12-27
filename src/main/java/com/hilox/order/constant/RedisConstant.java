package com.hilox.order.constant;

/**
 * redis常量
 * Created by Hilox on 2018/12/27 0027.
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200;  // 2小时
}
