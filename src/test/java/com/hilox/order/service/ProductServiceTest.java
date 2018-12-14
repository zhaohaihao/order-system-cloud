package com.hilox.order.service;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhh on 2018/12/14 0014.
 */
public class ProductServiceTest extends HiloxOrderApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findOneTest() throws Exception {
        Product product = productService.findOne("123456");
        Assert.assertEquals("123456", product.getId());
    }

    @Test
    public void findUpAllTest() throws Exception {
        List<Product> productList = productService.findUpAll();
        Assert.assertTrue(productList.size() != 0);
    }

    @Test
    public void findAllTest() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<Product> productPage = productService.findAll(pageRequest);
        System.out.println(productPage.getTotalElements());
    }

    @Test
    public void saveTest() throws Exception {
        Product product = new Product();
        product.setId("123457");
        product.setName("番茄炒蛋");
        product.setPrice(new BigDecimal(9));
        product.setStock(99);
        product.setDescription("好吃的番茄炒蛋");
        product.setIcon("http://test.jpg");
        product.setState(0);
        product.setCategoryCode(1);
        productService.save(product);
    }

}