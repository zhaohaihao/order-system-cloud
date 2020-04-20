package com.zhh.osc.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhh
 * @description
 * @date 2020-04-20 16:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockInput {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
