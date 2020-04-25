package com.zhh.osc.user.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 08:43
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色权限有误"),
    ;

    private Integer code;

    private String message;
}
