package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.common.enums.CashierDeskTypeEnum;
import com.fangcang.titanjr.dto.BaseRequestDTO;

public class RepairTransferRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//付款账户
	private String payermerchant;
	
	//订单状态
	private String status;
	
	private Integer transferStatus;
	
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

	public Integer getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(Integer transferStatus) {
		this.transferStatus = transferStatus;
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
