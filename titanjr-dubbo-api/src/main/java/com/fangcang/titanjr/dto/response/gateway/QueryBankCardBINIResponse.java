package com.fangcang.titanjr.dto.response.gateway;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class QueryBankCardBINIResponse extends RSBaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3029787256140055297L;
	
	private String merchantNo;
	
	//银行卡号
	private String cardNo;
	
	//所属银行编号
	private String bankCode;
	
	//所属银行名称
	private String bankName;
	
	//银行卡类型  10-储蓄卡，11-信用卡
	private String cardType;
	
	private String version;
	
	private String signType;
	
	private String signMsg;
	
	private String bankInfo;
	
	private int singleLimit;//单笔限额
	private int dailyLimit;//单日限额
	
	//是否需要校验信用卡的cvv码和有效期
	private boolean isValidAuth;
	

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	
	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public int getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(int singleLimit) {
		this.singleLimit = singleLimit;
	}

	public int getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(int dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public boolean isValidAuth() {
		return isValidAuth;
	}

	public void setValidAuth(boolean isValidAuth) {
		this.isValidAuth = isValidAuth;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
