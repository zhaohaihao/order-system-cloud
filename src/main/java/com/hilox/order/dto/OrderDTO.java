package com.hilox.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hilox.order.enums.OrderMasterPayStateEnum;
import com.hilox.order.enums.OrderMasterStateEnum;
import com.hilox.order.model.OrderDetail;
import com.hilox.order.utils.EnumUtil;
import com.hilox.order.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Hilox on 2018/12/21 0021.
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    /** 订单id **/
    private String id;

    /** 买家名字 **/
    private String buyerName;

    /** 买家电话 **/
    private String buyerPhone;

    /** 买家地址 **/
    private String buyerAddress;

    /** 买家微信openid **/
    private String buyerOpenid;

    /** 订单总金额 **/
    private BigDecimal amount;

    /** 订单状态, 默认0新下单 **/
    private Integer state;

    /** 支付状态, 默认0未支付 **/
    private Integer payState;

    /** 创建时间 **/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 修改时间 **/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderMasterStateEnum getOrderMasterStateEnum() {
        return EnumUtil.getByCode(state, OrderMasterStateEnum.class);
    }

    @JsonIgnore
    public OrderMasterPayStateEnum getOrderMasterPayStateEnum() {
        return EnumUtil.getByCode(payState, OrderMasterPayStateEnum.class);
    }
}
