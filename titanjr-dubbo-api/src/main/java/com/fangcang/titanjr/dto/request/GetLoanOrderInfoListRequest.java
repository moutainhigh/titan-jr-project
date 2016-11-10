package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;

public class GetLoanOrderInfoListRequest {

	private String orgCode;

	private String orderNo;

	private LoanOrderStatusEnum orderStatusEnum[];

	public LoanOrderStatusEnum[] getOrderStatusEnum() {
		return orderStatusEnum;
	}

	public void setOrderStatusEnum(LoanOrderStatusEnum... orderStatusEnum) {
		this.orderStatusEnum = orderStatusEnum;
	}

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
