package com.zhh.osc.product.service.impl;

import com.zhh.osc.product.dataobject.ProductInfo;
import com.zhh.osc.product.enums.ProductStatusEnum;
import com.zhh.osc.product.repository.ProductInfoRepository;
import com.zhh.osc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhh
 * @description 商品Service实现类
 * @date 2020-04-17 15:35
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
