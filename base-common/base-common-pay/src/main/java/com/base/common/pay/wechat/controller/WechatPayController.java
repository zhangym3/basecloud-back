package com.base.common.pay.wechat.controller;
import com.base.common.core.utils.DateUtils;
import com.base.common.core.utils.StreamUtils;
import com.alibaba.fastjson2.JSONObject;
import com.base.common.pay.wechat.bo.WxPayDTO;
import com.base.common.pay.wechat.properties.WxPayProperties;
import com.base.common.pay.wechat.service.PayService;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/pay")
public class WechatPayController {

    @Resource
    private PayService payService;
    @Resource
    private WxPayProperties wxPayProperties;

    @GetMapping("/nativePay")
    public Object nativePay(@RequestParam("orderNumber") String orderNumber) {
        //todo 业务操作-根据订单编号查询订单信息
        //将订单信息中的数据存到WxPayDTO
        WxPayDTO payDTO = new WxPayDTO();
        payDTO.setBody("商品描述");
        //订单总金额，单位为分
        payDTO.setTotalFee(1);
        //支付回调地址
        payDTO.setNotifyUrl(wxPayProperties.getNotifyUrl());
        //商品订单编号
        payDTO.setOutTradeNo(orderNumber);
        //获取时间
        Date date = new Date();
        String timeStart = DateUtils.formatDateToString(date, "yyyyMMddHHmmss");
        //结束时间设置在30分钟后
        String timeExpire = DateUtils.formatDateToString(DateUtils.addMinutesToDate(date, 30),"yyyyMMddHHmmss");
        //交易起始时间
        payDTO.setTimeStart(timeStart);
        //交易结束时间
        payDTO.setTimeExpire(timeExpire);
        Object url = payService.transactions(payDTO, WxPayConstants.TradeType.NATIVE);
        return url;
    }

    @GetMapping("/jsapiPay")
    public Object jsapiPay(@RequestParam("orderNumber") String orderNumber) {
        //todo 业务操作-根据订单编号查询订单信息
        //将订单信息中的数据存到WxPayDTO
        WxPayDTO payDTO = new WxPayDTO();
        payDTO.setBody("商品描述");
        //订单总金额，单位为分
        payDTO.setTotalFee(1);
        //支付回调地址
        payDTO.setNotifyUrl(wxPayProperties.getNotifyUrl());
        payDTO.setOutTradeNo("商户订单号");
        //获取时间
        Date date = new Date();
        String timeStart = DateUtils.formatDateToString(date, "yyyyMMddHHmmss");
        //结束时间设置在30分钟后
        String timeExpire = DateUtils.formatDateToString(DateUtils.addMinutesToDate(date, 30),"yyyyMMddHHmmss");
        //交易起始时间
        payDTO.setTimeStart(timeStart);
        //交易结束时间
        payDTO.setTimeExpire(timeExpire);
        //todo jsapi下单需要用户的openid
        payDTO.setOpenId("openid");
        Object url = payService.transactions(payDTO, WxPayConstants.TradeType.JSAPI);

        return url;
    }

    @GetMapping("/h5Pay")
    public Object h5Pay(@RequestParam("orderNumber") String orderNumber, HttpServletRequest request) {
        //todo 业务操作-根据订单编号查询订单信息

        //将订单信息中的数据存到WxPayDTO
        WxPayDTO payDTO = new WxPayDTO();
        payDTO.setBody("商品描述");
        //订单总金额，单位为分
        payDTO.setTotalFee(1);
        //支付回调地址
        payDTO.setNotifyUrl(wxPayProperties.getNotifyUrl());
        payDTO.setOutTradeNo("商户订单号");
        //获取时间
        Date date = new Date();
        String timeStart = DateUtils.formatDateToString(date, "yyyyMMddHHmmss");
        //结束时间设置在30分钟后
        String timeExpire = DateUtils.formatDateToString(DateUtils.addMinutesToDate(date, 30),"yyyyMMddHHmmss");
        //交易起始时间
        payDTO.setTimeStart(timeStart);
        //交易结束时间
        payDTO.setTimeExpire(timeExpire);
        //todo H5下单需要用户的用户的客户端IP
        String remoteAddr = request.getRemoteAddr();
        payDTO.setPayerClientIp(remoteAddr);
        Object url = payService.transactions(payDTO, WxPayConstants.TradeType.JSAPI);

        return url;
    }

    @GetMapping("/micropay")
    public Object micropay(@RequestParam("outTradeNo") String outTradeNo, @RequestParam("authCode") String authCode, HttpServletRequest request) {
        //todo 业务操作-根据订单编号查询订单信息

        //将订单信息中的数据存到WxPayDTO
        WxPayDTO payDTO = new WxPayDTO();
        payDTO.setBody("商品描述");
        //订单总金额，单位为分
        payDTO.setTotalFee(1);
        //支付回调地址
        payDTO.setNotifyUrl(wxPayProperties.getNotifyUrl());
        payDTO.setOutTradeNo(outTradeNo);
        payDTO.setAuthCode(authCode);
        //获取时间
        Date date = new Date();
        String timeStart = DateUtils.formatDateToString(date, "yyyyMMddHHmmss");
        //结束时间设置在30分钟后
        String timeExpire = DateUtils.formatDateToString(DateUtils.addMinutesToDate(date, 30),"yyyyMMddHHmmss");
        //交易起始时间
        payDTO.setTimeStart(timeStart);
        //交易结束时间
        payDTO.setTimeExpire(timeExpire);
        //todo H5下单需要用户的用户的客户端IP
        String remoteAddr = request.getRemoteAddr();
        payDTO.setPayerClientIp(remoteAddr);
        Object url = payService.transactions(payDTO, WxPayConstants.TradeType.MICROPAY);

        return url;
    }

    @PostMapping("/wechatCallback")
    public Object wechatCallback(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String notifyXml = StreamUtils.inputStream2String(inputStream, "utf-8");
            log.info(notifyXml);
            // 解析返回结果
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyXml);
            String jsonString = JSONObject.toJSONString(notifyMap);
            log.info(jsonString);
            // 判断支付是否成功
            if ("SUCCESS".equals(notifyMap.get("result_code"))) {
                //todo 修改订单状态

                // 支付成功：给微信发送我已接收通知的响应 创建响应对象
                Map<String, String> returnMap = new HashMap<>();
                returnMap.put("return_code", "SUCCESS");
                returnMap.put("return_msg", "OK");
                String returnXml = WXPayUtil.mapToXml(returnMap);
                response.setContentType("text/xml");
                log.info("支付成功");
                return returnXml;
            }else {
                //保存回调信息,方便排除问题
            }
            // 创建响应对象：微信接收到校验失败的结果后，会反复的调用当前回调函数
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("return_code", "FAIL");
            returnMap.put("return_msg", "");
            String returnXml = WXPayUtil.mapToXml(returnMap);
            response.setContentType("text/xml");
            log.info("校验失败");
            return returnXml;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}