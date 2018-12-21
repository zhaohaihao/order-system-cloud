package com.hilox.order.model;

import com.hilox.order.enums.OrderMasterPayStateEnum;
import com.hilox.order.enums.OrderMasterStateEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 * Created by Hilox on 2018/12/20 0020.
 */
@DynamicUpdate
@Entity
@Data
public class OrderMaster {

    /** 订单id **/
    @Id
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
    private Integer state = OrderMasterStateEnum.NEW.getCode();

    /** 支付状态, 默认0未支付 **/
    private Integer payState = OrderMasterPayStateEnum.WAIT.getCode();

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;
}
