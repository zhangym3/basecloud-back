package com.base.common.pay.wechat.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * wxpay pay properties.
 *
 * @author ruoyi
 */
@Data
@ConfigurationProperties(prefix = "wechat.pay")
public class WxPayProperties {
    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String appId;

    private String secret;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * 回调地址
     */
    private String notifyUrl;
}