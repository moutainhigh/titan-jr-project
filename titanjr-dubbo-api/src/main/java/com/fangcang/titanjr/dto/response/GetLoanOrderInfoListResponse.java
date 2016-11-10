package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.LoanApplyOrder;

public class GetLoanOrderInfoListResponse extends BaseRequestDTO {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private List<LoanApplyOrder> applyOrderInfo;

	public List<LoanApplyOrder> getApplyOrderInfo() {
		return applyOrderInfo;
	}

	public void setApplyOrderInfo(List<LoanApplyOrder> applyOrderInfo) {
		this.applyOrderInfo = applyOrderInfo;
	}
}
