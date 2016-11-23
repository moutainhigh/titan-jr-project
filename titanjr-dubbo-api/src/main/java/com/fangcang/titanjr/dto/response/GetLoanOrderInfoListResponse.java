package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;

public class GetLoanOrderInfoListResponse extends BaseRequestDTO {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private List<LoanApplyOrderBean> applyOrderInfo;

	public List<LoanApplyOrderBean> getApplyOrderInfo() {
		return applyOrderInfo;
	}

	public void setApplyOrderInfo(List<LoanApplyOrderBean> applyOrderInfo) {
		this.applyOrderInfo = applyOrderInfo;
	}
}
