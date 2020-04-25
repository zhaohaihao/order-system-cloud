package com.zhh.osc.user.server.service;

import com.zhh.osc.user.server.dataobject.UserInfo;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 08:38
 */
public interface UserService {

    /**
     * 通过openid来查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
