package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;

public class GetLoanOrderInfoResponse extends BaseResponseDTO {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private LoanApplyOrderBean applyOrderInfo;

	public LoanApplyOrderBean getApplyOrderInfo() {
		return applyOrderInfo;
	}

	public void setApplyOrderInfo(LoanApplyOrderBean applyOrderInfo) {
		this.applyOrderInfo = applyOrderInfo;
	}
}
