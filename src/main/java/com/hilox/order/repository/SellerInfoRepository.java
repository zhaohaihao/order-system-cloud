package com.hilox.order.repository;

import com.hilox.order.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息Repository
 * Created by Hilox on 2018/12/27 0027.
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);
}
