package com.fangcang.titanjr.task;

import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
/***
 * 授信申请资料提交融数
 * @author luoqinglong
 * @date 2017年1月3日
 */
public class CreditLoanRunnable implements Runnable{
	
	private TitanFinancialLoanCreditService loanCreditService;
	private String creditOrderNo;
	
	public CreditLoanRunnable(TitanFinancialLoanCreditService loanCreditService,String creditOrderNo){
		this.loanCreditService = loanCreditService;
		this.creditOrderNo = creditOrderNo;
	}
	
	@Override
	public void run() {
		loanCreditService.pushCreditInfoToRs(creditOrderNo);
	}
	
}
