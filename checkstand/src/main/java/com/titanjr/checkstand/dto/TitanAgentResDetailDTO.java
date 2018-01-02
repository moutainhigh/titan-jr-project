package com.titanjr.checkstand.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fangcang.titanjr.common.enums.WithDrawStatusEnum;

public class TitanAgentResDetailDTO {
	
	/**
	 * 商户订单号或者唯一流水号
	 */
	private String orderNo;
	
	/**
	 * 交易类型   0付   1收
	 */
	private String tradeType;

	/**
	 * 交易状态    {@link WithDrawStatusEnum}
	 */
	private String tradeStatus;
	
	/**
	 * 清算日期
	 */
	private String clearDate;
	
	/**
	 * 提交时间   yyyyMMddHHmmss
	 */
	private String submitTime;
	
	/**
	 * 账号
	 */
	private String accountNo;
	
	/**
	 * 账号名
	 */
	private String accountName;
	
	/**
	 * 交易金额
	 */
	private String tradeAmount;
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getClearDate() {
		return clearDate;
	}

	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}
}
