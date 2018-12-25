package com.hilox.order.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Hilox on 2018/12/25 0025.
 */
public class JsonUtil {

    /**
     * 对象转成json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
