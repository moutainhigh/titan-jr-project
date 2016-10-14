package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class TransOrderRequest extends BaseRequestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 订单Id
	private Integer transid;
	// 融数返回的订单Id
	private String orderid;
	// 系统生成的单Id
	private String userorderid;
	// 财务系统的业务单号
	private String businessordercode;
	// 机构id
	private String userid;
	// 财务系统支付单号
	private String payorderno;

	private String payertype;

	public String getPayertype() {
		return payertype;
	}

	public void setPayertype(String payertype) {
		this.payertype = payertype;
	}

	public String getPayorderno() {
		return payorderno;
	}

	public void setPayorderno(String payorderno) {
		this.payorderno = payorderno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getTransid() {
		return transid;
	}

	public void setTransid(Integer transid) {
		this.transid = transid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserorderid() {
		return userorderid;
	}

	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	public String getBusinessordercode() {
		return businessordercode;
	}

	public void setBusinessordercode(String businessordercode) {
		this.businessordercode = businessordercode;
	}

}
