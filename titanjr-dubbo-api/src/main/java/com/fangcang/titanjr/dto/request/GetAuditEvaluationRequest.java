package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 
 * @author Administrator
 *
 */
public class GetAuditEvaluationRequest  extends BaseRequestDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
