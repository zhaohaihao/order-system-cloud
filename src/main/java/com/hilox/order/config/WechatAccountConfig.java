package com.hilox.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账户信息配置
 * Created by Hilox on 2018/12/25 0025.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台appId
     */
    private String mpAppId;

    /**
     * 公众平台appSecret
     */
    private String mpAppSecret;

    /**
     * 微信开放平台appId
     */
    private String openAppId;

    /**
     * 微信开放平台appSecret
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}
