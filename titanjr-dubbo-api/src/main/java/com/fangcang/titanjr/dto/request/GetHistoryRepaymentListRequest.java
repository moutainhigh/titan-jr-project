package com.fangcang.titanjr.dto.request;
/**
 * 獲取還款歷史請求
 * @author wengxitao
 *
 */
public class GetHistoryRepaymentListRequest implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5692654155074493850L;

	private String orgCode;
	
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
