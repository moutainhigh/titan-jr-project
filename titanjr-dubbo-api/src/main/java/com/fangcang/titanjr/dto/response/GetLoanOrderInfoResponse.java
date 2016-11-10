package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.LoanApplyOrder;

public class GetLoanOrderInfoResponse extends BaseRequestDTO {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private LoanApplyOrder applyOrderInfo;

	public LoanApplyOrder getApplyOrderInfo() {
		return applyOrderInfo;
	}

	public void setApplyOrderInfo(LoanApplyOrder applyOrderInfo) {
		this.applyOrderInfo = applyOrderInfo;
	}
}
