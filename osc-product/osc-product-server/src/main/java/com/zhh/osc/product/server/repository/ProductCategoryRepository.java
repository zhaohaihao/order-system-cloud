package com.zhh.osc.product.server.repository;

import com.zhh.osc.product.server.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhh
 * @description 商品类目Repository
 * @date 2020-04-17 15:30
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
