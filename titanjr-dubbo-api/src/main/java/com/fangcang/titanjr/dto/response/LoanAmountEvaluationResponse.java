package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class LoanAmountEvaluationResponse extends BaseResponseDTO{

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private String maxCreditAmount;

	public String getMaxCreditAmount() {
		return maxCreditAmount;
	}

	public void setMaxCreditAmount(String maxCreditAmount) {
		this.maxCreditAmount = maxCreditAmount;
	}

}
