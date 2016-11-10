package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.LoanRepaymentInfo;

public class GetHistoryRepaymentListResponse extends BaseRequestDTO 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<LoanRepaymentInfo> loanRepaymentInfos;

	public List<LoanRepaymentInfo> getLoanRepaymentInfos() {
		return loanRepaymentInfos;
	}

	public void setLoanRepaymentInfos(List<LoanRepaymentInfo> loanRepaymentInfos) {
		this.loanRepaymentInfos = loanRepaymentInfos;
	}
	
	
}
