package com.hilox.order.service;

import com.hilox.order.HiloxOrderApplicationTests;
import com.hilox.order.dto.OrderDTO;
import com.hilox.order.enums.OrderMasterPayStateEnum;
import com.hilox.order.enums.OrderMasterStateEnum;
import com.hilox.order.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilox on 2018/12/21 0021.
 */
public class OrderServiceTest extends HiloxOrderApplicationTests {

    @Autowired
    private OrderService orderService;

    private static final String BUYER_OPENID = "999999";

    @Test
    public void createTest() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Hilox");
        orderDTO.setBuyerPhone("12345678999");
        orderDTO.setBuyerAddress("地球");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setAmount(new BigDecimal(2.5));

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123457");
        orderDetail.setProductQuantity(5);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1545379539915162785");
        Assert.assertEquals("1545379539915162785", orderDTO.getId());
    }

    @Test
    public void findList() throws Exception {
        Pageable pageable = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList("999999", pageable);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1545379539915162785");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderMasterStateEnum.CANCEL.getCode(), result.getState());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1545379539915162785");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderMasterStateEnum.FINISHED.getCode(), result.getState());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1545379539915162785");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(OrderMasterPayStateEnum.SUCCESS.getCode(), result.getPayState());
    }

}