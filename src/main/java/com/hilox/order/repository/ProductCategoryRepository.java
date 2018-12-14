package com.hilox.order.repository;

import com.hilox.order.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目Repository
 * Created by Hilox on 2018/12/7 0007.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据指定商品类目编号查询商品类目
     * @param codeList 商品类目编号集合
     * @return
     */
    List<ProductCategory> findByCodeIn(List<Integer> codeList);
}
