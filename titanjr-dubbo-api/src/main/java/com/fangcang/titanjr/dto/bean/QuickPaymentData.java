package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 快捷支付数据
 * @author fangdaikang
 *
 */
public class QuickPaymentData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//订单号
	private String orderNo;
	
	//用户名
	private String userName;
	
	//付款密码
	private String payPassword;
	
	//银行卡
	private String accountNumber;
	
	//总行名称
	private String bankHeadName;
	
	//总行联号
	private String bankHead;
	
	//是否需要绑卡 0:绑卡 1:已绑卡
	private String isBindCard;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankHeadName() {
		return bankHeadName;
	}

	public void setBankHeadName(String bankHeadName) {
		this.bankHeadName = bankHeadName;
	}

	public String getBankHead() {
		return bankHead;
	}

	public void setBankHead(String bankHead) {
		this.bankHead = bankHead;
	}

	public String getIsBindCard() {
		return isBindCard;
	}

	public void setIsBindCard(String isBindCard) {
		this.isBindCard = isBindCard;
	}
	
}
