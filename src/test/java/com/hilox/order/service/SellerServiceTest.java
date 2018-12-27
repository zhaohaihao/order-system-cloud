package com.hilox.order.service;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Hilox on 2018/12/27 0027.
 */
public class SellerServiceTest extends HiloxOrderApplicationTests {

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenidTest() throws Exception {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid("999999");
        Assert.assertEquals("999999", sellerInfo.getOpenid());
    }

}