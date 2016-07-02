package com.fangcang.titanjr.dto.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.enums.TradeTypeEnum;
import com.fangcang.titanjr.dto.BaseRequestDTO;

public class TradeDetailRequest extends BaseRequestDTO {

    @NotNull
    private String userid;

    //业务单号
    private String businessordercode;

    //查询类型
    private TradeTypeEnum tradeTypeEnum;
    //交易类型id
    private String tradeTypeId;

    //开始时间
    private Date startTime;
    //开始时间字串
    private String startTimeStr;
    //结束时间
    private Date endTime;
    //结束时间字串
    private String endTimeStr;
    //对方名称
    private String admissionName;
    //订单金额，能够根据订单金额来查询
    private String orderAmount;
    //房仓生成的订单id
    private String userOrderId;

    private String orderOperator;

    //是否冻结
    private String isEscrowedPayment;

    public TradeTypeEnum getTradeTypeEnum() {
        return tradeTypeEnum;
    }

    public void setTradeTypeEnum(TradeTypeEnum tradeTypeEnum) {
        this.tradeTypeEnum = tradeTypeEnum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBusinessordercode() {
        return businessordercode;
    }

    public void setBusinessordercode(String businessordercode) {
        this.businessordercode = businessordercode;
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

    public String getAdmissionName() {
        return admissionName;
    }

    public void setAdmissionName(String admissionName) {
        this.admissionName = admissionName;
    }

    public String getTradeTypeId() {
        return tradeTypeId;
    }

    public void setTradeTypeId(String tradeTypeId) {
        this.tradeTypeId = tradeTypeId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getOrderOperator() {
        return orderOperator;
    }

    public void setOrderOperator(String orderOperator) {
        this.orderOperator = orderOperator;
    }

    public String getIsEscrowedPayment() {
        return isEscrowedPayment;
    }

    public void setIsEscrowedPayment(String isEscrowedPayment) {
        this.isEscrowedPayment = isEscrowedPayment;
    }

}
