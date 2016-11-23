package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;

/**
 * 请求保存更新贷款单信息
 * 
 * @author wengxitao
 *
 */
public class SaveLoanOrderInfoRequest extends BaseRequestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//贷款单号
	private String orderNo;
	//组织编号
	private String orgCode;
	
	//贷款信息
	private LoanApplyOrderBean orderInfo;

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

	public LoanApplyOrderBean getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(LoanApplyOrderBean orderInfo) {
		this.orderInfo = orderInfo;
	}
}
