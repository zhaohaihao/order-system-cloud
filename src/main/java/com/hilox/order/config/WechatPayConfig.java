package com.hilox.order.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信网页支付配置
 * Created by Hilox on 2018/12/25 0025.
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        return wxPayH5Config;
    }
}
