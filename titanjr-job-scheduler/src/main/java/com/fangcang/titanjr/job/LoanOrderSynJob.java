package com.fangcang.titanjr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.LoanOrderSynRunner;
import com.fangcang.titanjr.service.TitanFinancialLoanService;

public class LoanOrderSynJob extends QuartzJobBean{
	
	private TitanFinancialLoanService titanFinancialLoanService;
	
	private ThreadPoolTaskExecutor titanJobExecutor;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		LoanOrderSynRunner task = new LoanOrderSynRunner(
				titanFinancialLoanService);
		
		titanJobExecutor.execute(task);
	}
	
	public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
		this.titanJobExecutor = titanJobExecutor;
	}
	public void setTitanFinancialLoanService(
			TitanFinancialLoanService titanFinancialLoanService) {
		this.titanFinancialLoanService = titanFinancialLoanService;
	}
}
