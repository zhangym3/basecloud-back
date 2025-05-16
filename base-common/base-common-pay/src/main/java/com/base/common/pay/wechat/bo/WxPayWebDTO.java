package com.base.common.pay.wechat.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayWebDTO {
    /**
     * 公众号id
     */
    private String appId;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 订单详情扩展字符串
     */
    private String package1;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 签名
     */
    private String paySign;
}