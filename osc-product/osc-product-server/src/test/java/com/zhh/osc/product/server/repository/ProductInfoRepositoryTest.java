package com.zhh.osc.product.server.repository;

import com.zhh.osc.product.server.OscProductApplicationTests;
import com.zhh.osc.product.server.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 15:26
 */
public class ProductInfoRepositoryTest extends OscProductApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size() > 0);
    }
}