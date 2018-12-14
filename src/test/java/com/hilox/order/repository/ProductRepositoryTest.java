package com.hilox.order.repository;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Hilox on 2018/12/14 0014.
 */
public class ProductRepositoryTest extends HiloxOrderApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveTest() throws Exception {
        Product product = new Product();
        product.setId("123456");
        product.setName("蛋炒饭");
        product.setPrice(new BigDecimal(5));
        product.setStock(99);
        product.setDescription("好吃的蛋炒饭");
        product.setIcon("http://test.jpg");
        product.setState(0);
        product.setCategoryCode(1);
        productRepository.save(product);
    }

    @Test
    public void findByStateTest() throws Exception {
        List<Product> productList = productRepository.findByState(0);
        Assert.assertTrue(productList.size() != 0);
    }

}