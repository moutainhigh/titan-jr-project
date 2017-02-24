package com.fangcang.titanjr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.LoanCreditAmountEvaluatingRunner;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;

public class LoanCreditAmountEvaluatingJob  extends QuartzJobBean{
	
	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;
	
	private TitanFinancialOrganService financialOrganService;
	
	
	private ThreadPoolTaskExecutor titanJobExecutor;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		LoanCreditAmountEvaluatingRunner task = new LoanCreditAmountEvaluatingRunner(
				financialOrganService, titanFinancialLoanCreditService);
		
		titanJobExecutor.execute(task);
	}
	
	public void setFinancialOrganService(
			TitanFinancialOrganService financialOrganService) {
		this.financialOrganService = financialOrganService;
	}
	
	public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
		this.titanJobExecutor = titanJobExecutor;
	}
	public void setTitanFinancialLoanCreditService(
			TitanFinancialLoanCreditService titanFinancialLoanCreditService) {
		this.titanFinancialLoanCreditService = titanFinancialLoanCreditService;
	}

}
