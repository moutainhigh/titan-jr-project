package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.LoanCreditOpinionBean;

public class GetAuditEvaluationResponse extends BaseResponseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoanCreditOpinionBean creditOpinionBean;

	public LoanCreditOpinionBean getCreditOpinionBean() {
		return creditOpinionBean;
	}

	public void setCreditOpinionBean(LoanCreditOpinionBean creditOpinionBean) {
		this.creditOpinionBean = creditOpinionBean;
	}

}
