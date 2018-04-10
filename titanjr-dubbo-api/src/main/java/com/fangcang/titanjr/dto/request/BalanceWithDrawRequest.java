package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class BalanceWithDrawRequest extends BaseRequestDTO {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6716873626800513261L;


	//提现金额 单位为分 100分为1元
	@NotNull
	private String amount;
	
	
	//订单日期  格式2015-06-09 23:59:59		日期+时间
	@NotNull
	private String orderDate;
	
	//商户系统的订单id
	@NotNull
	private String userorderid;
	
	@NotNull
	private String productid;
	
	//------------可选---------
	
	private String orderNo;
	
	// 手续费（元）
	// 应收手续费
	private String receivablefee = "0";
	// 实收手续费（元）
	private String receivedfee = "0";
	// 标准费率手续费（元）
	private String standfee = "0";

	@NotNull
	private String userId;
	// 银行名称
	private String bankName;

	private String cardNo;
	
	private String creator;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserorderid() {
		return userorderid;
	}

	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getUserId() {
		return userId;
	}

	public String getReceivablefee() {
		return receivablefee;
	}

	public void setReceivablefee(String receivablefee) {
		this.receivablefee = receivablefee;
	}

	public String getReceivedfee() {
		return receivedfee;
	}

	public void setReceivedfee(String receivedfee) {
		this.receivedfee = receivedfee;
	}

	public String getStandfee() {
		return standfee;
	}

	public void setStandfee(String standfee) {
		this.standfee = standfee;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
