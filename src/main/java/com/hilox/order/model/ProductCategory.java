package com.hilox.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目
 * Created by Hilox on 2018/12/7 0007.
 */
@Entity
@Data
@DynamicUpdate // 动态更新
public class ProductCategory {

    /** 类目id **/
    @Id
    @GeneratedValue
    private Integer id;

    /** 类目名称 **/
    private String name;

    /** 类目编号 **/
    private Integer code;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;
}
