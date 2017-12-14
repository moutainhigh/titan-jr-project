package com.fangcang.titanjr.rest.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/8/7.
 */
@ApiModel(value = "JRTransOrderDTO", description = "交易单信息")
public class JRTransOrderDTO {

    @ApiModelProperty(value = "付款方金融机构编码")
    private String payerMerchant;
    @ApiModelProperty(value = "收款方金融机构编码")
    private String payeeMerchant;
    @ApiModelProperty(value = "订单类型ID")
    private String orderTypeId;
    @ApiModelProperty(value = "上游订单ID")
    private String orderId;
    @ApiModelProperty(value = "金融系统订单ID")
    private String userOrderId;
    @ApiModelProperty(value = "业务订单号")
    private String businessOrderCode;
    @ApiModelProperty(value = "订单日期")
    private Date orderDate;
    @ApiModelProperty(value = "订单时间")
    private Date orderTime;
    @ApiModelProperty(value = "订单总金额")
    private Long amount;
    // 交易金额
    @ApiModelProperty(value = "订单交易总金额")
    private Long tradeAmount;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "商品详情")
    private String goodsDetail;
    @ApiModelProperty(value = "商品数量")
    private Integer goodsCnt;
    @ApiModelProperty(value = "单价")
    private Double unitPrice;
    @ApiModelProperty(value = "订单状态")
    private String statusId;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "币种")
    private String amtType;
    @ApiModelProperty(value = "订单手续费")
    private Long receivedFee;
    @ApiModelProperty(value = "商家编码")
    private String merchantCode;
    @ApiModelProperty(value = "支付订单号，业务系统传入")
    private String payOrderNo;
    @ApiModelProperty(value = "业务系统回调地址")
    private String notifyUrl;
    @ApiModelProperty(value = "订单备注信息")
    private String remark;
    // 交易类型
    @ApiModelProperty(value = "订单交易类型")
    private String tradeType;
    // 对方
    @ApiModelProperty(value = "交易对方")
    private String transTarget;
    // 银行卡号
    @ApiModelProperty(value = "银行编码")
    private String bankCode;
    // 银行名称
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    // 银行标识
    @ApiModelProperty(value = "银行表示")
    private String bankInfo;
    @ApiModelProperty(value = "是否担保支付")
    private String isEscrowedPayment;
    @ApiModelProperty(value = "担保结束日期")
    private Date escrowedDate;
    // 业务信息
    @ApiModelProperty(value = "业务信息")
    private String businessInfo;
    @ApiModelProperty(value = "付款方类型")
    private String payerType;
    @ApiModelProperty(value = "交易单类型")
    private Integer transOrderType;
    @ApiModelProperty(value = "贷款单号")
    private String loanOrderNo;

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getBusinessOrderCode() {
        return businessOrderCode;
    }

    public void setBusinessOrderCode(String businessOrderCode) {
        this.businessOrderCode = businessOrderCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Long tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public Integer getGoodsCnt() {
        return goodsCnt;
    }

    public void setGoodsCnt(Integer goodsCnt) {
        this.goodsCnt = goodsCnt;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAmtType() {
        return amtType;
    }

    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }

    public Long getReceivedFee() {
        return receivedFee;
    }

    public void setReceivedFee(Long receivedFee) {
        this.receivedFee = receivedFee;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getPayeeMerchant() {
        return payeeMerchant;
    }

    public void setPayeeMerchant(String payeeMerchant) {
        this.payeeMerchant = payeeMerchant;
    }

    public String getPayerMerchant() {
        return payerMerchant;
    }

    public void setPayerMerchant(String payerMerchant) {
        this.payerMerchant = payerMerchant;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTransTarget() {
        return transTarget;
    }

    public void setTransTarget(String transTarget) {
        this.transTarget = transTarget;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getIsEscrowedPayment() {
        return isEscrowedPayment;
    }

    public void setIsEscrowedPayment(String isEscrowedPayment) {
        this.isEscrowedPayment = isEscrowedPayment;
    }

    public Date getEscrowedDate() {
        return escrowedDate;
    }

    public void setEscrowedDate(Date escrowedDate) {
        this.escrowedDate = escrowedDate;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

    public String getPayerType() {
        return payerType;
    }

    public void setPayerType(String payerType) {
        this.payerType = payerType;
    }

    public Integer getTransOrderType() {
        return transOrderType;
    }

    public void setTransOrderType(Integer transOrderType) {
        this.transOrderType = transOrderType;
    }

    public String getLoanOrderNo() {
        return loanOrderNo;
    }

    public void setLoanOrderNo(String loanOrderNo) {
        this.loanOrderNo = loanOrderNo;
    }

}
