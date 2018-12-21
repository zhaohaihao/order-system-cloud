package com.hilox.order.service;

import com.hilox.order.dto.CartDTO;
import com.hilox.order.dto.OrderDTO;
import com.hilox.order.enums.ResultEnum;
import com.hilox.order.exception.SellException;
import com.hilox.order.model.OrderDetail;
import com.hilox.order.model.OrderMaster;
import com.hilox.order.model.Product;
import com.hilox.order.repository.OrderDetailRepository;
import com.hilox.order.repository.OrderMasterRepository;
import com.hilox.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Service
 * Created by Hilox on 2018/12/21 0021.
 */
@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

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
            orderAmount = orderDetail.getProductPrice()
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
            cartDTO.setProductId(orderDetail.getId());
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        // 3. 写入订单数据库(order_master和order_detail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setId(orderId);
        orderMaster.setAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    public OrderDTO findOne(String orderId) {
        return null;
    }

    /**
     * 查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    public OrderDTO paid(OrderDTO orderDTO) {
       return null;
    }
}
