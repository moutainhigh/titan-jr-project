package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class BalanceWithDrawRequest extends BaseRequestDTO {

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
	//100 手续费（分）
	private Long userFee;

	@NotNull
	private String userId;

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

	public Long getUserFee() {
		return userFee;
	}

	public void setUserFee(Long userFee) {
		this.userFee = userFee;
	}

	public String getUserId() {
		return userId;
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
}
