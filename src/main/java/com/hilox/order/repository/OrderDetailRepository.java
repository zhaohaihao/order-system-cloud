package com.hilox.order.repository;

import com.hilox.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情Repository
 * Created by Hilox on 2018/12/20 0020.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 查询某订单的订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
