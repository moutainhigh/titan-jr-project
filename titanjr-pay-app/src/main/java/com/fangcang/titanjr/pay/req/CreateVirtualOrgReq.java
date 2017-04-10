package com.fangcang.titanjr.pay.req;

public class CreateVirtualOrgReq {
	
	private String userId;
	
	private String bankName;
	private String bankCode;
	private String cardNum;
	private String accountName;
	// 开户行支行号
	private String bankBranch;
	private String bankCityCode;
	private String bankCityName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankCityCode() {
		return bankCityCode;
	}
	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}
	public String getBankCityName() {
		return bankCityName;
	}
	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}
}
