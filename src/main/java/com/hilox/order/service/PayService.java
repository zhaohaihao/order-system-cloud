package com.hilox.order.service;

import com.hilox.order.dto.OrderDTO;
import com.hilox.order.enums.ResultEnum;
import com.hilox.order.exception.SellException;
import com.hilox.order.utils.JsonUtil;
import com.hilox.order.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付Service
 * Created by Hilox on 2018/12/25 0025.
 */
@Service
@Slf4j
public class PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param orderDTO
     */
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付, request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付, response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    /**
     * 异步通知
     * @param notifyData
     */
    public PayResponse notify(String notifyData) {
        // 1. 验证签名
        // 2. 支付的状态
        // 3. 支付的金额
        // 4. 支付人(下单人 == 支付人)

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知, payResponse={}", JsonUtil.toJson(payResponse));

        // 查询订单是否存在
        String orderId = payResponse.getOrderId();
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            log.info("【微信支付】异步通知, 订单不存在, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 判断金额是否一致
        if (!MathUtil.equals(orderDTO.getAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.info("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}", orderId, payResponse.getOrderAmount(), orderDTO.getAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        // 修改订单的支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }
}
