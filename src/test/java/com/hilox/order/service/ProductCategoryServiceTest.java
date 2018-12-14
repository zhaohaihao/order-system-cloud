package com.hilox.order.service;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hilox on 2018/12/14 0014.
 */
public class ProductCategoryServiceTest extends HiloxOrderApplicationTests {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOneTest() throws Exception {
        ProductCategory productCategory = productCategoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getId());
    }

    @Test
    public void findAllTest() throws Exception {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        Assert.assertTrue(productCategoryList.size() != 0);
    }

    @Test
    public void findByCodeInTest() throws Exception {
        List<Integer> codeList = Arrays.asList(1, 2);
        List<ProductCategory> productCategoryList = productCategoryService.findByCodeIn(codeList);
        Assert.assertTrue(productCategoryList.size() != 0);
    }

    @Test
    public void saveTest() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("女生最爱");
        productCategory.setCode(3);
        productCategoryService.save(productCategory);
    }

}