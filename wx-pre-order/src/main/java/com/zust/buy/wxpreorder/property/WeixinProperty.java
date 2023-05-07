package com.zust.buy.wxpreorder.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class WeixinProperty {

    /**
     * 登录凭证校验请求地址
     */
    private String jscode2sessionUrl;

    /**
     * 小程序 appId
     */
    private String appId;

    /**
     * 小程序 密码
     */
    private String secret;
}
