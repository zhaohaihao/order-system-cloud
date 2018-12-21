package com.hilox.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 * Created by Hilox on 2018/12/20 0020.
 */
@DynamicUpdate
@Entity
@Data
public class OrderDetail {

    /** 订单详情id **/
    @Id
    private String id;

    /** 订单id **/
    private String orderId;

    /** 商品id **/
    private String productId;

    /** 商品名称 **/
    private String productName;

    /** 商品价格 **/
    private BigDecimal productPrice;

    /** 商品数量 **/
    private Integer productQuantity;

    /** 商品图片 **/
    private String productIcon;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;
}
