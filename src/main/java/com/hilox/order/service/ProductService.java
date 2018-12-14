package com.hilox.order.service;

import com.hilox.order.enums.ProductStateEnum;
import com.hilox.order.model.Product;
import com.hilox.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品Service
 * Created by Hilox on 2018/12/14 0014.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 根据商品Id查询商品信息
     * @param productId 商品Id
     * @return
     */
    public Product findOne(String productId) {
        return productRepository.findOne(productId);
    }

    /**
     * 查询所有已上架的商品
     * @return
     */
    public List<Product> findUpAll() {
        return productRepository.findByState(ProductStateEnum.UP.getCode());
    }

    /**
     * 分页查询所有的商品
     * @param pageable 分页信息
     * @return
     */
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * 新增或者更新商品
     * @param product 商品信息
     * @return
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
