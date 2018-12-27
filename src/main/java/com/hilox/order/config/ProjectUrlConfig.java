package com.hilox.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目地址配置
 * Created by Hilox on 2018/12/27 0027.
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    /**
     * 微信公众账号授权url
     */
    public String wechatMpAuthorizeUrl;

    /**
     * 微信开放平台授权url
     */
    public String wechatOpenAuthorizeUrl;

    /**
     * 项目url
     */
    public String hilox;
}
