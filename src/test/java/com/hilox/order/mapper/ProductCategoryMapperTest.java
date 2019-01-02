package com.hilox.order.mapper;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hilox on 2019/1/2 0002.
 */
public class ProductCategoryMapperTest extends HiloxOrderApplicationTests {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "特色菜");
        map.put("code", 4);
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("创意菜");
        productCategory.setCode(5);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCode() throws Exception {
        ProductCategory productCategory = productCategoryMapper.findByCode(5);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateByCode() throws Exception {
        int result = productCategoryMapper.updateByCode("难吃", 5);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("创意菜");
        productCategory.setCode(5);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }
}