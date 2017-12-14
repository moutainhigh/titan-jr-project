package com.fangcang.titanjr.rest.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/8/7.
 */
@ApiModel(value = "JRTitanTransferDTO", description = "转账单信息")
public class JRTitanTransferDTO {

    @ApiModelProperty(value = "付款方机构编码")
    private String userId;
    @ApiModelProperty(value = "付款方账户ID")
    private String productId;
    @ApiModelProperty(value = "收款方机构编码")
    private String userRelateId;
    @ApiModelProperty(value = "收款方账户ID")
    private String interProductId;
    @ApiModelProperty(value = "转账金额")
    private Double amount;
    @ApiModelProperty(value = "手续费")
    private Double userFee;
    @ApiModelProperty(value = "请求号")
    private String requestNo;
    @ApiModelProperty(value = "请求时间")
    private Date requestTime;
    @ApiModelProperty(value = "标示备注")
    private String remark;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "手续费")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "付款单号")
    private String payOrderNo;


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

    public String getUserRelateId() {
        return userRelateId;
    }

    public void setUserRelateId(String userRelateId) {
        this.userRelateId = userRelateId;
    }

    public String getInterProductId() {
        return interProductId;
    }

    public void setInterProductId(String interProductId) {
        this.interProductId = interProductId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUserFee() {
        return userFee;
    }

    public void setUserFee(Double userFee) {
        this.userFee = userFee;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }
}
