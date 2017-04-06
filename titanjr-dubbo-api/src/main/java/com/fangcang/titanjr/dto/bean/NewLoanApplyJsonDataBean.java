package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 个人申请贷款jsondata
 * @author luoqinglong
 * @2016年11月9日
 */
public class NewLoanApplyJsonDataBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3835243369426563201L;
	private String loanApplicateName;//申请人
	private String inParty;//收款方
	private String userOrderId;//订单编号
	private String orderTime;//订单时间
	private String productName;//产品名称
	private String inBankAccount;//收款方开户行
	private String inBankAccountNo;//收款方银行账号
	private String deliveryStatus;//发货状态
	private String receivingState;//收货状态
	private String receiptAddress;//收货地址
	private String orderStatus;//订单状态
	private String code;//串码
	private String unitPrice;//单价
	private String number;//数量
	private String orderAmount;//订单总额（分）
	private String orderType;//订单类型
	private String rootInstCd;//机构号码
	private String loanTerm;//贷款期限（天）
	private String shenqingAmount;
	
	public String getShenqingAmount() {
		return shenqingAmount;
	}
	
	public void setShenqingAmount(String shenqingAmount) {
		this.shenqingAmount = shenqingAmount;
	}
	public String getLoanApplicateName() {
		return loanApplicateName;
	}
	public void setLoanApplicateName(String loanApplicateName) {
		this.loanApplicateName = loanApplicateName;
	}
	public String getInParty() {
		return inParty;
	}
	public void setInParty(String inParty) {
		this.inParty = inParty;
	}
	public String getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInBankAccount() {
		return inBankAccount;
	}
	public void setInBankAccount(String inBankAccount) {
		this.inBankAccount = inBankAccount;
	}
	public String getInBankAccountNo() {
		return inBankAccountNo;
	}
	public void setInBankAccountNo(String inBankAccountNo) {
		this.inBankAccountNo = inBankAccountNo;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getReceivingState() {
		return receivingState;
	}
	public void setReceivingState(String receivingState) {
		this.receivingState = receivingState;
	}
	public String getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getRootInstCd() {
		return rootInstCd;
	}
	public void setRootInstCd(String rootInstCd) {
		this.rootInstCd = rootInstCd;
	}
	public String getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	
	
}
