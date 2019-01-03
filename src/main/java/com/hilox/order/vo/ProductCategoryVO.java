package com.hilox.order.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品类目
 * Created by Hilox on 2018/12/19 0019.
 */
@Data
public class ProductCategoryVO implements Serializable {

    private static final long serialVersionUID = -6738049293826486794L;

    /** 类目名称 **/
    private String name;

    /** 类目编号 **/
    private Integer code;

    /** 商品详情列表 **/
    @JsonProperty("foods")
    private List<ProductVO> productInfoVOList;
}
