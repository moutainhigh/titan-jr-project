package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/5/13.
 */
public class CashierDeskQueryRequest implements Serializable {
    //付款方机构编码，可能是分销商，商家
    private String payerOrgCode;
    //收款方机构编码，可能时商家，供应商，酒店
    private String payeeOrgCode;
    //付款来源：1.账单支付，2.联盟分销商供应商付款，3.GDP支付，4.移动端付款
    private Integer paySource;

    private String userId;

    private Long deskId;

    private Integer usedFor;
    
    //开启状态 0关闭 1开启
    private String isOpen;

    public String getPayerOrgCode() {
        return payerOrgCode;
    }

    public void setPayerOrgCode(String payerOrgCode) {
        this.payerOrgCode = payerOrgCode;
    }

    public String getPayeeOrgCode() {
        return payeeOrgCode;
    }

    public void setPayeeOrgCode(String payeeOrgCode) {
        this.payeeOrgCode = payeeOrgCode;
    }

    public Integer getPaySource() {
        return paySource;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getDeskId() {
        return deskId;
    }

    public void setDeskId(Long deskId) {
        this.deskId = deskId;
    }

    public void setPaySource(Integer paySource) {
        this.paySource = paySource;
    }

    public Integer getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(Integer usedFor) {
        this.usedFor = usedFor;
    }

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
}
