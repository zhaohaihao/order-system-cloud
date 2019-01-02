package com.hilox.order.service;

import com.hilox.order.converter.OrderMaster2OrderDTOConverter;
import com.hilox.order.dto.CartDTO;
import com.hilox.order.dto.OrderDTO;
import com.hilox.order.enums.OrderMasterPayStateEnum;
import com.hilox.order.enums.OrderMasterStateEnum;
import com.hilox.order.enums.ResultEnum;
import com.hilox.order.exception.SellException;
import com.hilox.order.model.OrderDetail;
import com.hilox.order.model.OrderMaster;
import com.hilox.order.model.Product;
import com.hilox.order.repository.OrderDetailRepository;
import com.hilox.order.repository.OrderMasterRepository;
import com.hilox.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Service
 * Created by Hilox on 2018/12/21 0021.
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        String orderId = KeyUtil.genUniqueKey();

        List<CartDTO> cartDTOList = new ArrayList<>();
        // 1. 查询商品(数量, 价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            Product product = productService.findOne(orderDetail.getProductId());
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2. 计算订单总价
            orderAmount = product.getPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情入库
            orderDetail.setId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductName(product.getName());
            orderDetail.setProductIcon(product.getIcon());
            orderDetail.setProductPrice(product.getPrice());
            orderDetailRepository.save(orderDetail);

            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(orderDetail.getProductId());
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        // 3. 写入订单数据库(order_master和order_detail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setAmount(orderAmount);
        orderMaster.setState(OrderMasterStateEnum.NEW.getCode());
        orderMaster.setPayState(OrderMasterPayStateEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        productService.decreaseStock(cartDTOList);

        // 发送webSocket消息
        webSocket.sendMessage(orderDTO.getId());

        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    /**
     * 查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态
        if (orderDTO.getState() != OrderMasterStateEnum.NEW.getCode()) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getId(), orderDTO.getState());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setState(OrderMasterStateEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(orderDetail.getProductId());
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        productService.increaseStock(cartDTOList);

        // 如果已支付, 需要退款
        if (orderDTO.getPayState() == OrderMasterPayStateEnum.SUCCESS.getCode()) {
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if (orderDTO.getState() != OrderMasterStateEnum.NEW.getCode()) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getId(), orderDTO.getState());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setState(OrderMasterStateEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 推送微信模板消息
        pushMessageService.orderStatus(orderDTO);

        return orderDTO;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if (orderDTO.getState() != OrderMasterStateEnum.NEW.getCode()) {
            log.error("【订单支付成功】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getId(), orderDTO.getState());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if (orderDTO.getPayState() != OrderMasterPayStateEnum.WAIT.getCode()) {
            log.error("【订单支付成功】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改支付状态
        orderDTO.setPayState(OrderMasterPayStateEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null) {
            log.error("【订单支付成功】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    /**
     * 分页查询订单列表
     * @param pageable
     * @return
     */
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }
}
