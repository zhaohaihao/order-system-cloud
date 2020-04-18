package com.zhh.osc.order.repository;

import com.zhh.osc.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 16:05
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
