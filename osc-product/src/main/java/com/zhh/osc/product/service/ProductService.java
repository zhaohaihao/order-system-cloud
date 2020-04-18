package com.zhh.osc.product.service;

import com.zhh.osc.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author zhh
 * @description 商品Service接口
 * @date 2020-04-17 15:35
 */
public interface ProductService {

    /**
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();
}
