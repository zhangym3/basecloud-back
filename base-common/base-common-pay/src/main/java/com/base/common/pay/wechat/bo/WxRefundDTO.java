package com.base.common.pay.wechat.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxRefundDTO {
    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 订单金额 单位:分
     */
    private Integer totalFee;

    /**
     * 退款金额 单位:分
     */
    private Integer refundFee;

    /**
     * 退款结果通知url
     */
    private String notifyUrl;
}
