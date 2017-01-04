package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 包房信息
 * 
 * @author fangdaikang
 *
 */
public class LoanApplyInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 贷款单号
	private String loanOrderNo;

	// 申请金额
	private String amount;

	// 酒店名称
	private String hotelName;

	// 包房开始时间
	private String beginDate;

	// 包房结束时间
	private String endDate;

	// 间夜数
	private String roomNights;

	// 账户名
	private String accountName;

	// 银行卡号
	private String account;

	private String titanCode;

	private String content;

	// 开户行
	private String bank;

	// 文件名称
	private String contactNames;

	private String productType;

	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitanCode() {
		return titanCode;
	}

	public void setTitanCode(String titanCode) {
		this.titanCode = titanCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRoomNights() {
		return roomNights;
	}

	public void setRoomNights(String roomNights) {
		this.roomNights = roomNights;
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

	public String getContactNames() {
		return contactNames;
	}

	public void setContactNames(String contactNames) {
		this.contactNames = contactNames;
	}

	public String getLoanOrderNo() {
		return loanOrderNo;
	}

	public void setLoanOrderNo(String loanOrderNo) {
		this.loanOrderNo = loanOrderNo;
	}

}
