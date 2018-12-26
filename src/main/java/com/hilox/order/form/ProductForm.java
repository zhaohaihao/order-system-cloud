package com.hilox.order.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Hilox on 2018/12/26 0026.
 */
@Data
public class ProductForm {

    /** 商品id **/
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

    /** 商品类目编号 **/
    private Integer categoryCode;
}
