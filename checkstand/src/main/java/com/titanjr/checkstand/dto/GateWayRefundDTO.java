package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class GateWayRefundDTO {

    //商户号
    @NotBlank
    private String merchantNo;
    //业务号
    @NotBlank
    private String busiCode;
    //退款单号
    @NotBlank
    private String refundOrderno;
    //订单号
    @NotBlank
    private String orderNo;
    //订单时间
    @NotBlank
    private String orderTime;
    //退款时间
    @NotBlank
    private String refundAmount;
    //版本号
    @NotBlank
    private String version;
    //签名类型
    @NotBlank
    private String signType;
    //签名信息
    @NotBlank
    private String signMsg;

    //appid应用id
    private String appid;
    //原交易单号，第三方支付使用
    private String oldtrxid;
    //随机字串
    private String randomstr;
    //退款类型
    private String refundType;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getRefundOrderno() {
        return refundOrderno;
    }

    public void setRefundOrderno(String refundOrderno) {
        this.refundOrderno = refundOrderno;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getOldtrxid() {
        return oldtrxid;
    }

    public void setOldtrxid(String oldtrxid) {
        this.oldtrxid = oldtrxid;
    }

    public String getRandomstr() {
        return randomstr;
    }

    public void setRandomstr(String randomstr) {
        this.randomstr = randomstr;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }
}
