package com.base.common.pay.wechat.service;


import com.alibaba.fastjson2.JSONObject;
import com.base.common.pay.wechat.bo.*;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 支付相关通用服务
 */
@Service
public class PayService {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 根据传入的tradeType来进行不同类型的支付
     * @param dto 商品参数
     * @param tradeType WxPayConstants.TradeType.MWEB为H5支付/WxPayConstants.TradeType.NATIVE为扫码支付/WxPayConstants.TradeType.JSAPI为JSAPI支付
     * @return
     */
    public Object transactions(WxPayDTO dto, String tradeType) {
        try {
            if (tradeType != null && !tradeType.isEmpty()){
                if (WxPayConstants.TradeType.MWEB.equals(tradeType)){
                    //H5支付
                    return noMiniappPay(dto, tradeType);
                }else if (WxPayConstants.TradeType.NATIVE.equals(tradeType)){
                    //NATIVE支付
                    return noMiniappPay(dto, tradeType);
                }else if (WxPayConstants.TradeType.JSAPI.equals(tradeType)){
                    //JSAPI支付
                    return miniappPay(dto);
                }else if (WxPayConstants.TradeType.MICROPAY.equals(tradeType)){
                    //JSAPI支付
                    return micropay(dto);
                }
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private String noMiniappPay(WxPayDTO dto, String tradeType) throws WxPayException {
        //设置请求参数
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setOutTradeNo(dto.getOutTradeNo());
        request.setTotalFee(dto.getTotalFee());
        request.setBody(dto.getBody());
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        request.setSpbillCreateIp(httpServletRequest.getRemoteAddr());
        request.setNotifyUrl(dto.getNotifyUrl());
        request.setProductId(dto.getOutTradeNo());
        //设置下单方式
        request.setTradeType(tradeType);
        // 调用统一下单接口
        WxPayNativeOrderResult result = wxPayService.createOrder(request);
        // Native支付返回的二维码/H5支付返回的跳转链接
        String codeUrl = result.getCodeUrl();
        // 返回codeUrl给前端
        return codeUrl;
    }
    public WxPayMicropayResult micropay(WxPayDTO dto) throws WxPayException {
        WxPayMicropayRequest request = new WxPayMicropayRequest();
        request.setAuthCode(dto.getAuthCode());
        request.setBody(dto.getBody());
        request.setOutTradeNo(dto.getOutTradeNo());
        request.setTotalFee(dto.getTotalFee());
        request.setSpbillCreateIp(dto.getPayerClientIp());
        return wxPayService.micropay(request);
    }
    /**
     *
     * @param dto
     * @return
     */
    @SneakyThrows
    public Map<String, String> miniappPay(WxPayDTO dto){
        //设置请求参数
        WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
                .body(dto.getBody())
                .totalFee(dto.getTotalFee())
                .spbillCreateIp(this.getIpAddress(this.getHttpServletRequest()))
                .notifyUrl(dto.getNotifyUrl())
                .tradeType(WxPayConstants.TradeType.JSAPI)
                .openid(dto.getOpenId())
                .outTradeNo(dto.getOutTradeNo())
                .timeStart(dto.getTimeStart())
                .timeExpire(dto.getTimeExpire())
                .build();
        request.setSignType(WxPayConstants.SignType.MD5);

        //发起微信 统一下单
        WxPayUnifiedOrderResult result = null;
        result = this.wxPayService.unifiedOrder(request);
        //前端启动支付配置
        if (StringUtils.isBlank(result.getPrepayId())) {
            return null;
        }
        //返回前端调取jsapi支付的所需参数
        return getWxJsapiPayParam(result);
    }

    /**
     * 微信退款
     * @param body
     * @return
     */
    public void wxRefund(WxRefundDTO body) {
        WxPayRefundRequest refundRequest = new WxPayRefundRequest();
        refundRequest.setOutTradeNo(body.getOutTradeNo());
        refundRequest.setOutRefundNo(body.getOutRefundNo());
        refundRequest.setTotalFee(body.getTotalFee());
        refundRequest.setRefundFee(body.getRefundFee());
        refundRequest.setRefundDesc("商品退款");
        refundRequest.setNotifyUrl(body.getNotifyUrl());

        try {
            WxPayRefundResult result = wxPayService.refund(refundRequest);
            System.out.println("微信退款成功,返回参数{}" + JSONObject.toJSONString(result));
        } catch (WxPayException e) {
            System.out.println("微信退款异常,返回参数{}" + JSONObject.toJSONString(e));
        }
    }


    /**
     * 获取请求对象
     *
     * @return
     */
    private HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            System.out.println("请求头：" + key + "值：" + request.getHeader(key));
        }
        return request;
    }

    /**
     * 获取到JS支付相关配置
     *
     * @param weiXinPayOrder
     * @return
     */
    private Map<String, String> getWxJsapiPayParam(WxPayUnifiedOrderResult weiXinPayOrder) {
        WxPayWebDTO wxPayParam = new WxPayWebDTO();
        String package_ = "prepay_id=" + weiXinPayOrder.getPrepayId();
        wxPayParam.setAppId(weiXinPayOrder.getAppid());
        wxPayParam.setTimeStamp(System.currentTimeMillis() / 1000 + "");
        wxPayParam.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
        wxPayParam.setPackage1(package_);
        wxPayParam.setSignType(WxPayConstants.SignType.MD5);
        //WxPayParam中属性package1，签名使用key是package
        Map<String, String> signParam = new LinkedHashMap<String, String>();
        signParam.put("appId", weiXinPayOrder.getAppid());
        signParam.put("nonceStr", wxPayParam.getNonceStr());
        signParam.put("package", package_);
        signParam.put("signType", WxPayConstants.SignType.MD5);
        signParam.put("timeStamp", wxPayParam.getTimeStamp());
        String paySign = SignUtils.createSign(signParam,WxPayConstants.SignType.MD5, this.wxPayService.getConfig().getMchKey(), new String[0]);
        signParam.put("paySign", paySign);
        return signParam;
    }
    private String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest 不能为空");
        }

        // 常见的代理转发头部
        String[] headers = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        // 遍历这些头部信息，寻找真实 IP
        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                // 如果有多个 IP 地址（通过代理链），取第一个非 unknown 的地址
                return ip.split(",")[0].trim();
            }
        }

        // 如果没有找到代理头，则返回 RemoteAddr
        return request.getRemoteAddr();
    }
}
