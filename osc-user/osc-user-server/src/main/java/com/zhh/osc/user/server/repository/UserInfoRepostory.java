package com.zhh.osc.user.server.repository;

import com.zhh.osc.user.server.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhh
 * @description
 * @date 2020-04-23 08:37
 */
public interface UserInfoRepostory extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
