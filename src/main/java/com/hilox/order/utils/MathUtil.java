package com.hilox.order.utils;

/**
 * Created by Hilox on 2018/12/25 0025.
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个数据相等
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1, Double d2) {
        double abs = Math.abs(d1 - d2);
        if (abs < MONEY_RANGE) {
            return true;
        }
        return false;
    }
}
