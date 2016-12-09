package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.LoanRepaymentBean;

public class GetHistoryRepaymentListResponse extends BaseResponseDTO 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<LoanRepaymentBean> loanRepaymentInfos;

	public List<LoanRepaymentBean> getLoanRepaymentInfos() {
		return loanRepaymentInfos;
	}

	public void setLoanRepaymentInfos(List<LoanRepaymentBean> loanRepaymentInfos) {
		this.loanRepaymentInfos = loanRepaymentInfos;
	}
	
	
}
