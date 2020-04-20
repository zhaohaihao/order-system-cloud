package com.zhh.osc.product.client;

import com.zhh.osc.product.common.DecreaseStockInput;
import com.zhh.osc.product.common.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zhh
 * @description
 * @date 2020-04-20 16:39
 */
@FeignClient(name = "osc-product")
public interface ProductClient {

    /**
     * 获取商品列表(给订单服务用的)
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
