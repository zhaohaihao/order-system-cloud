package com.hilox.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * Created by Hilox on 2018/12/14 0014.
 */
@Entity
@Data
@DynamicUpdate
public class Product {

    /** 商品id **/
    @Id
    private String id;

    /** 商品名称 **/
    private String name;

    /** 商品单价 **/
    private BigDecimal price;

    /** 商品库存 **/
    private Integer stock;

    /** 商品描述 **/
    private String description;

    /** 商品图片 **/
    private String icon;

    /** 商品状态，0正常 1下架 **/
    private Integer state;

    /** 商品类目编号 **/
    private Integer categoryCode;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;
}
