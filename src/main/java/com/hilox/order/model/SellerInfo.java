package com.hilox.order.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 卖家信息
 * Created by Hilox on 2018/12/27 0027.
 */
@Data
@Entity
@DynamicUpdate
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;
}
