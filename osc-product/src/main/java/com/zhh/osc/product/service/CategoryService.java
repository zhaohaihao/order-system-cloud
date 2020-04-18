package com.zhh.osc.product.service;

import com.zhh.osc.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 15:40
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
