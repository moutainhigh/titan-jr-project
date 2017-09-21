package com.fangcang.titanjr.rest.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/8/7.
 */
@ApiModel(value = "JRTitanOrderPayDTO", description = "充值单信息")
public class JRTitanOrderPayDTO {

    @ApiModelProperty(value = "上游订单号")
    private String orderNo;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "产品描述")
    private String productDesc;
    @ApiModelProperty(value = "订单金额")
    private Double orderAmount;
    @ApiModelProperty(value = "付款类型")
    private String payType;
    @ApiModelProperty(value = "币种")
    private String amtType;
    @ApiModelProperty(value = "银行编码")
    private String bankInfo;
    @ApiModelProperty(value = "订单时间")
    private String orderTime;
    @ApiModelProperty(value = "订单过期时间，单位分钟")
    private Integer orderExpireTime;
    @ApiModelProperty(value = "手续费")
    private Long receivedFee;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAmtType() {
        return amtType;
    }

    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderExpireTime() {
        return orderExpireTime;
    }

    public void setOrderExpireTime(Integer orderExpireTime) {
        this.orderExpireTime = orderExpireTime;
    }

    public Long getReceivedFee() {
        return receivedFee;
    }

    public void setReceivedFee(Long receivedFee) {
        this.receivedFee = receivedFee;
    }
}
