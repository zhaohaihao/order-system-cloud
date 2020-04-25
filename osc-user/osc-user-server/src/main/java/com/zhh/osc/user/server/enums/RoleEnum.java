package com.zhh.osc.user.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 08:44
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;

    private String message;
}
