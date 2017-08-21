package com.fangcang.titanjr.rest.request;

import com.fangcang.titanjr.rest.enums.TradeTypeEnum;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/8/4.
 */
@ApiModel(value = "TradeDetailRequest", description = "交易记录查询请求")
public class JRTransQueryRequest {

    @ApiModelProperty(value = "查询付款方机构编码",required = true)
    private String userId;
    @ApiModelProperty(value = "业务单号")
    private String businessOrderCode;
    @ApiModelProperty(value = "支付单号")
    private String payOrderNo;
    @ApiModelProperty(value = "交易类型.0:交易记录，1:付款记录，2:收款记录，3:充值记录，4:提现记录",
            allowableValues = "TRADE_RECORD,PAYMENT_RECORD,PAYEE_RECORD,RECHARGE_RECORD,WITHDRAW_RECORD", required = true)
    private TradeTypeEnum tradeTypeEnum;
    @ApiModelProperty(value = "查询起始日期,格式yyyy-MM-dd'T'HH:mm:ss.SSSZ ，比如2017-07-08T15:23:44.000")
    private Date startTime;
    @ApiModelProperty(value = "查询结束日期,格式yyyy-MM-dd'T'HH:mm:ss.SSSZ ，比如2017-07-08T15:23:44.000")
    private Date endTime;
    @ApiModelProperty(value = "交易对方名称")
    private String tradeTarget;
    @ApiModelProperty(value = "交易金额")
    private String orderAmount;
    @ApiModelProperty(value = "房仓交易单号")
    private String userOrderId;
    @ApiModelProperty(value = "订单操作人")
    private String orderOperator;
    @ApiModelProperty(value = "是否担保操作,0担保，1不担保")
    private String isEscrowedPayment;

    @ApiModelProperty(value = "分页参数：当前页编号")
    private int currentPage = 1;
    @ApiModelProperty(value = "分页参数：每页数量")
    private int pageSize = 15;

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
        if ("".equals(payOrderNo)){
            this.payOrderNo = null;
        }
    }

    public TradeTypeEnum getTradeTypeEnum() {
        return tradeTypeEnum;
    }

    public void setTradeTypeEnum(TradeTypeEnum tradeTypeEnum) {
        this.tradeTypeEnum = tradeTypeEnum;
        if ("".equals(tradeTypeEnum)){
            this.tradeTypeEnum = TradeTypeEnum.TRADE_RECORD;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessOrderCode() {
        return businessOrderCode;
    }

    public void setBusinessOrderCode(String businessOrderCode) {
        this.businessOrderCode = businessOrderCode;
        if ("".equals(businessOrderCode)){
            this.businessOrderCode = null;
        }
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTradeTarget() {
        return tradeTarget;

    }

    public void setTradeTarget(String tradeTarget) {
        this.tradeTarget = tradeTarget;
        if ("".equals(tradeTarget)){
            this.tradeTarget = null;
        }
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
        if ("".equals(orderAmount)){
            this.orderAmount = null;
        }
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
        if ("".equals(userOrderId)){
            this.userOrderId = null;
        }
    }

    public String getOrderOperator() {
        return orderOperator;
    }

    public void setOrderOperator(String orderOperator) {
        this.orderOperator = orderOperator;
        if ("".equals(orderOperator)){
            this.orderOperator = null;
        }
    }

    public String getIsEscrowedPayment() {
        return isEscrowedPayment;
    }

    public void setIsEscrowedPayment(String isEscrowedPayment) {
        this.isEscrowedPayment = isEscrowedPayment;
        if ("".equals(isEscrowedPayment)){
            this.isEscrowedPayment = null;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
