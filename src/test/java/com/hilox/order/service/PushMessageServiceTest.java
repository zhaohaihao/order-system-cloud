package com.hilox.order.service;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.dto.OrderDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhh on 2018/12/29 0029.
 */
public class PushMessageServiceTest extends HiloxOrderApplicationTests {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatusTest() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1545379539915162785");
        pushMessageService.orderStatus(orderDTO);
    }

}