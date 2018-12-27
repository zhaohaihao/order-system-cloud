package com.hilox.order.service;

import com.hilox.order.model.SellerInfo;
import com.hilox.order.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家Service
 * Created by Hilox on 2018/12/27 0027.
 */
@Service
public class SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 根据openid查找卖家信息
     * @param openid
     * @return
     */
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
