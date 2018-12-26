package com.hilox.order.utils;

import com.hilox.order.enums.CodeEnum;

/**
 * 枚举方法工具类
 * Created by Hilox on 2018/12/26 0026.
 */
public class EnumUtil {

    /**
     * 获取对应code枚举类
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
