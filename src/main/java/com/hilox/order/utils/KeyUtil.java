package com.hilox.order.utils;

import java.util.Random;

/**
 * 主键工具类
 * Created by Hilox on 2018/12/21 0021.
 */
public class KeyUtil {

    /**
     * 位数
     */
    private static final Integer DIGIT = 100000;

    // 构造私有化
    private KeyUtil() {}

    /**
     * 生成唯一主键
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(9 * DIGIT) + DIGIT;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
