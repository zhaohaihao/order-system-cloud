package com.hilox.order.service;

import com.hilox.order.model.ProductCategory;
import com.hilox.order.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目Service
 * Created by Hilox on 2018/12/14 0014.
 */
@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 根据商品类目Id查询商品类目信息
     * @param categoryId 商品类目Id
     * @return
     */
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.findOne(categoryId);
    }

    /**
     * 查询所有商品类目
     * @return
     */
    public List<ProductCategory> findAll() {
       return productCategoryRepository.findAll();
    }

    /**
     * 根据指定商品类目编号查询商品类目
     * @param codeList 商品类目编号集合
     * @return
     */
    public List<ProductCategory> findByCodeIn(List<Integer> codeList) {
        return productCategoryRepository.findByCodeIn(codeList);
    }

    /**
     * 新增或者更新商品类目
     * @param productCategory 商品类目信息
     * @return
     */
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
