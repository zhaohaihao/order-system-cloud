package com.hilox.order.repository;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hilox on 2018/12/7 0007.
 */
public class ProductCategoryRepositoryTest extends HiloxOrderApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() throws Exception {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        Assert.assertTrue(productCategory != null);
    }

    @Test
    public void saveTest() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("男生最爱");
        productCategory.setCode(2);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCodeInTest() throws Exception {
        List<Integer> codeList = Arrays.asList(1,2);
        List<ProductCategory> list = productCategoryRepository.findByCodeIn(codeList);
        Assert.assertTrue(list != null && list.size() != 0);
    }
}