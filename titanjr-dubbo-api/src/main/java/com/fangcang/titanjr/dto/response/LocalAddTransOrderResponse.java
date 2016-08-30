package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class LocalAddTransOrderResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//本地生成的单号，用于冻结资金
	private String orderNo;
	
	//金融系统流水单号
	private String userOrderId;
	
	private Integer transid;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public Integer getTransid() {
		return transid;
	}

	public void setTransid(Integer transid) {
		this.transid = transid;
	}

}
