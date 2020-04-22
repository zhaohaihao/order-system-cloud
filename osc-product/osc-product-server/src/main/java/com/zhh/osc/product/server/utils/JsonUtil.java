package com.zhh.osc.product.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author zhh
 * @description
 * @date 2020-04-21 21:51
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转换为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转对象
     * @param string
     * @param clazzType
     * @return
     */
    public static Object fromJson(String string, Class clazzType) {
        try {
            return objectMapper.readValue(string, clazzType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
