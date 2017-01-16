package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 取消贷款请求接口
 * @author wengxitao
 *
 */
public class CancelLoanRequest extends BaseRequestDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//组织编号
	private String orgCode;
	
	// 贷款单号
	private String orderNo;
	
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
