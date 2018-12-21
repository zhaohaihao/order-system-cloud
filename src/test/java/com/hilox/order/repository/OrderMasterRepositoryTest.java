package com.hilox.order.repository;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

/**
 * Created by Hilox on 2018/12/20 0020.
 */
public class OrderMasterRepositoryTest extends HiloxOrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId("123457");
        orderMaster.setBuyerName("Hilox");
        orderMaster.setBuyerPhone("12345678999");
        orderMaster.setBuyerAddress("地球");
        orderMaster.setBuyerOpenid("999999");
        orderMaster.setAmount(new BigDecimal(2.5));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenidTest() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 1);

        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid("999999", pageRequest);
        Assert.assertNotEquals(0, orderMasters.getTotalElements());
    }
}