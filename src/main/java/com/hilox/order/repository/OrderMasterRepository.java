package com.hilox.order.repository;

import com.hilox.order.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单Repository
 * Created by Hilox on 2018/12/20 0020.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 分页查询买家的订单
     * @param buyerOpenid 买家微信openid
     * @param pageable 分页信息
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
