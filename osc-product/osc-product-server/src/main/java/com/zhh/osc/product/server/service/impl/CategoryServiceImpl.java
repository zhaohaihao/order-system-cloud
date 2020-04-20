package com.zhh.osc.product.server.service.impl;

import com.zhh.osc.product.server.dataobject.ProductCategory;
import com.zhh.osc.product.server.repository.ProductCategoryRepository;
import com.zhh.osc.product.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 15:41
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
