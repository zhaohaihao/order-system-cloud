package com.hilox.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 * Created by Hilox on 2018/12/19 0019.
 */
@Data
public class ProductVO {

    /** 商品id **/
    private String id;

    /** 商品名称 **/
    private String name;

    /** 商品单价 **/
    private BigDecimal price;

    /** 商品描述 **/
    private String description;

    /** 商品图片 **/
    private String icon;
}
