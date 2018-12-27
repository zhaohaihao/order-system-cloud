package com.hilox.order.repository;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.SellerInfo;
import com.hilox.order.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Hilox on 2018/12/27 0027.
 */
public class SellerInfoRepositoryTest extends HiloxOrderApplicationTests {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void saveTest() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("999999");
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenidTest() throws Exception {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("999999");
        Assert.assertEquals("999999", sellerInfo.getOpenid());
    }

}