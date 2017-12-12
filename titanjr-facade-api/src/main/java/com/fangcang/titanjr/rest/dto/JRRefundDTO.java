package com.fangcang.titanjr.rest.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/8/7.
 */
@ApiModel(value = "JRTitanWithDrawDTO", description = "退款单信息")
public class JRRefundDTO {

    @ApiModelProperty(value = "业务信息")
    private String busiCode;
    @ApiModelProperty(value = "上游订单号")
    private String orderNo;
    @ApiModelProperty(value = "退款金额")
    private String refundAmount;
    @ApiModelProperty(value = "退款单号")
    private String refundOrderNo;
    @ApiModelProperty(value = "手续费")
    private String orderTime;
    @ApiModelProperty(value = "退款人")
    private String creator;
    @ApiModelProperty(value = "当前状态")
    private Integer status;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "交易金额")
    private String transferAmount;
    @ApiModelProperty(value = "手续费")
    private String fee;
    @ApiModelProperty(value = "支付单号")
    private String payOrderNo;
    @ApiModelProperty(value = "通知地址")
    private String notifyUrl;
    @ApiModelProperty(value = "金融工单号")
    private String userOrderId;
    @ApiModelProperty(value = "业务信息")
    private String businessInfo;

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }
}
