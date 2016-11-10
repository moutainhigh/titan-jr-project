package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 授信数据保存请求
 * 
 * @author wengxitao
 *
 */
public class ApplyLoanCreditRequest extends BaseRequestDTO {
	/**
	 */
	private static final long serialVersionUID = 1L;

	// 授信单号
	private String orderNo;
	
	//金服授信用户ID
	private String orgCode;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	
}
