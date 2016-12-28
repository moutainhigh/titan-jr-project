package com.fangcang.titanjr.dto.bean;

public class LoanSpecificationBean extends LoanSpecBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderNo;
	
	private String accountName;
	
	private String account;
	
	private String bank;
	
	private String titanCode;
	
	private String content;
	
	private String accessory;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getTitanCode() {
		return titanCode;
	}

	public void setTitanCode(String titanCode) {
		this.titanCode = titanCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
}
