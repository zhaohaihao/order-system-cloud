package com.zhh.osc.order.server.repository;

import com.zhh.osc.order.server.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhh
 * @description
 * @date 2020-04-17 16:05
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
