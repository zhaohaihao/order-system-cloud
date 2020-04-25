package com.zhh.osc.user.server.service.impl;

import com.zhh.osc.user.server.dataobject.UserInfo;
import com.zhh.osc.user.server.repository.UserInfoRepostory;
import com.zhh.osc.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 08:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepostory repostory;
    /**
     * 通过openid来查询用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        return repostory.findByOpenid(openid);
    }
}
