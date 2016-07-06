package com.fangcang.titanjr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.BindBankCardRunner;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;

public class BindBankCardJob extends QuartzJobBean{
	
	private TitanFinancialBankCardService titanFinancialBankCardService;

	private ThreadPoolTaskExecutor titanJobExecutor;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {

    	System.out.println("111111111111");
    	BindBankCardRunner task = new BindBankCardRunner(titanFinancialBankCardService);

        titanJobExecutor.execute(task);
        System.out.println(titanFinancialBankCardService + "execute===job");
	}

}
