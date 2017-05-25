package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 当前请求用于查出交易单支付失败但充值成功的
 * 这种可能是转账失败导致
 */
public class RepairTransferRequest extends BaseRequestDTO{

	//付款账户
	private String payermerchant;
	
	//交易单支付状态
	private String status;

	//充值状态
	private Integer orderPayStatus;
	
	private String orderid;
	
	private String userOrderId;
	
	private String payOrderNo;

	public String getPayermerchant() {
		return payermerchant;
	}

	public void setPayermerchant(String payermerchant) {
		this.payermerchant = payermerchant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOrderPayStatus() {
		return orderPayStatus;
	}

	public void setOrderPayStatus(Integer orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
	

}
