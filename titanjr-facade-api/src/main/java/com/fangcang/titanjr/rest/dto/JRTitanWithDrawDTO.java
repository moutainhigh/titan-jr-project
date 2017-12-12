package com.fangcang.titanjr.rest.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/8/7.
 */
@ApiModel(value = "JRTitanWithDrawDTO", description = "提现单信息")
public class JRTitanWithDrawDTO {
    @ApiModelProperty(value = "提款方机构编码")
    private String userId;
    @ApiModelProperty(value = "付款方账户ID")
    private String productId;
    @ApiModelProperty(value = "金融系统订单号")
    private String userOrderId;
    @ApiModelProperty(value = "订单日期")
    private String orderDate;
    @ApiModelProperty(value = "交易金额")
    private Long amount;
    @ApiModelProperty(value = "手续费")
    private Long userFee;
    @ApiModelProperty(value = "提现状态")
    private Integer status;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "实收手续费")
    private Float receivedFee;
    @ApiModelProperty(value = "提现银行名称")
    private String bankName;
    @ApiModelProperty(value = "提现银行编码")
    private String bankCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUserFee() {
        return userFee;
    }

    public void setUserFee(Long userFee) {
        this.userFee = userFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Float getReceivedFee() {
        return receivedFee;
    }

    public void setReceivedFee(Float receivedFee) {
        this.receivedFee = receivedFee;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
