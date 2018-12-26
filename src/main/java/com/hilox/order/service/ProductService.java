package com.hilox.order.service;

import com.hilox.order.dto.CartDTO;
import com.hilox.order.enums.ProductStateEnum;
import com.hilox.order.enums.ResultEnum;
import com.hilox.order.exception.SellException;
import com.hilox.order.model.Product;
import com.hilox.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 加库存
     * @param cartDTOList
     */
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            Product product = productRepository.findOne(cartDTO.getProductId());
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int result = product.getStock() + cartDTO.getProductQuantity();
            product.setStock(result);
            productRepository.save(product);
        }
    }

    /**
     * 减库存
     * @param cartDTOList
     */
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            Product product = productRepository.findOne(cartDTO.getProductId());
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int result = product.getStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            product.setStock(result);
            productRepository.save(product);
        }
    }

    /**
     * 商品上架
     * @param productId 商品id
     * @return
     */
    public Product onSale(String productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getProductStateEnum() == ProductStateEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATE_ERROR);
        }

        // 更新
        product.setState(ProductStateEnum.UP.getCode());
        productRepository.save(product);
        return product;
    }

    /**
     * 商品下架
     * @param productId 商品id
     * @return
     */
    public Product offSale(String productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getProductStateEnum() == ProductStateEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATE_ERROR);
        }

        // 更新
        product.setState(ProductStateEnum.DOWN.getCode());
        productRepository.save(product);
        return product;
    }
}
