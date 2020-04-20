package com.zhh.osc.order.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhh
 * @description
 * @date 2020-04-20 15:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
