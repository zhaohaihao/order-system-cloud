package com.hilox.order.repository;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Hilox on 2018/12/20 0020.
 */
public class OrderDetailRepositoryTest extends HiloxOrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId("111112");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("番茄炒蛋");
        orderDetail.setProductPrice(new BigDecimal(9));
        orderDetail.setProductQuantity(1);
        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void findByOrderIdTest() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("123456");
        Assert.assertNotEquals(0, orderDetailList.size());
    }

}