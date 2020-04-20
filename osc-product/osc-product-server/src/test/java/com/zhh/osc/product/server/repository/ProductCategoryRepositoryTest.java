package com.zhh.osc.product.server.repository;

import com.zhh.osc.product.server.OscProductApplicationTests;
import com.zhh.osc.product.server.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 15:33
 */
public class ProductCategoryRepositoryTest extends OscProductApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 12));
        Assert.assertTrue(list.size() > 0);
    }
}