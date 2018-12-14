package com.hilox.order.repository;

import com.hilox.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品Repository
 * Created by Hilox on 2018/12/14 0014.
 */
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * 查询对应状态的商品
     * @param state 商品状态，0正常 1下架
     * @return
     */
    List<Product> findByState(Integer state);
}
