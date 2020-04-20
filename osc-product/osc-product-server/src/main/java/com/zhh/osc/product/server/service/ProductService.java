package com.zhh.osc.product.server.service;

import com.zhh.osc.product.common.DecreaseStockInput;
import com.zhh.osc.product.common.ProductInfoOutput;
import com.zhh.osc.product.server.dataobject.ProductInfo;

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

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
