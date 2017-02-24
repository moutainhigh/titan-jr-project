package com.fangcang.titanjr.dto.request;

import java.util.Date;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 落单+绑卡
 * @author fangdaikang
 */
public class OrderSaveAndBindCardRequest extends BaseRequestDTO{

	private static final long serialVersionUID = 1L;
	
	//用户id 必须 
	private String userId;
	
	//机构号  必须 
	private String constId;
	
	//产品号 必须 
	private String productId;
	
	//订单类型(固定为 ： BX1） 必须 
	private String orderTypeId = "BX1";
	
	//请求时间  必须   	（格式固定： yyyy-MM-dd HH:mm:ss)
	private Date orderTime;
	
	//平台请求号 必须 
	private String userOrderId;
	
	//金额 必须 
	private String amount;
	
	//卡号 必须 
	private String accountNumber;
	
	//开户行总行名称 必须 
	private String bankHeadName;
	
	//币种（CNY）  必须 
	private String currency;
	
	//账号类型 00银行卡，01存折，02信用卡。
	private String accountTypeId;
	
	//账户目的 (1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)  必须 
	private String accountPurpose;
	
	//账户属性  （1：对公，2：对私）  必须 
	private String accountProperty;
	
	//开户证件类型  0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5. 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7. 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件  必须 
	private String certificateType;
	
	//证件号
	private String certificateNumber;
	
	//账号名  银行卡或存折上的所有人姓名。必须 
	private String accountName;
	
	//银行总行联行号(总行的标识) 必须 
	private String bankHead;
	
	//手机号  可选  不填写时请留空，
	private String goodsName;
	
	//注册时间 （String类型，yyyyMMddHHmmss)  可选 ，不填写时请留空
	private String goodsDetail;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConstId() {
		return constId;
	}

	public void setConstId(String constId) {
		this.constId = constId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountPurpose() {
		return accountPurpose;
	}

	public void setAccountPurpose(String accountPurpose) {
		this.accountPurpose = accountPurpose;
	}

	public String getAccountProperty() {
		return accountProperty;
	}

	public void setAccountProperty(String accountProperty) {
		this.accountProperty = accountProperty;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankHead() {
		return bankHead;
	}

	public void setBankHead(String bankHead) {
		this.bankHead = bankHead;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public String getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	
}
