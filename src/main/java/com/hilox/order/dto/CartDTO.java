package com.hilox.order.dto;

import lombok.Data;

/**
 * 购物车
 * Created by Hilox on 2018/12/21 0021.
 */
@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
