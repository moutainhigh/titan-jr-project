package com.fangcang.titanjr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.LoanCreditSynRunner;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;

public class LoanCreditSynJob extends QuartzJobBean {

	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;
	
	private ThreadPoolTaskExecutor titanJobExecutor;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		LoanCreditSynRunner task = new LoanCreditSynRunner(
				titanFinancialLoanCreditService);
		
		titanJobExecutor.execute(task);

	}

	public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
		this.titanJobExecutor = titanJobExecutor;
	}

	public void setTitanFinancialLoanCreditService(
			TitanFinancialLoanCreditService titanFinancialLoanCreditService) {
		this.titanFinancialLoanCreditService = titanFinancialLoanCreditService;
	}
}
