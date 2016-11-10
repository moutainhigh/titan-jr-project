package com.fangcang.titanjr.dto.request;


/**
 * 授信数据保存请求
 * 
 * @author wengxitao
 *
 */
public class GetCreditInfoRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	// 授信单号
	private String orderNo;
	
	// 金服组织编号
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
