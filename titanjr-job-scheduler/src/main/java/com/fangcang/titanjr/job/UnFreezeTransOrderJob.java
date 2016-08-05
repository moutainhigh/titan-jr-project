package com.fangcang.titanjr.job;


import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.UnFreezeTransOrderRunner;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by zhaoshan on 2016/6/20.
 */
public class UnFreezeTransOrderJob extends QuartzJobBean {


    private TitanFinancialTradeService titanFinancialTradeService;

    private TitanFinancialAccountService titanFinancialAccountService;

    private ThreadPoolTaskExecutor titanJobExecutor;

    protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {

        UnFreezeTransOrderRunner task = new UnFreezeTransOrderRunner(titanFinancialAccountService);

        titanJobExecutor.execute(task);
    }

    
   
    public void setTitanFinancialTradeService(TitanFinancialTradeService titanFinancialTradeService) {
        this.titanFinancialTradeService = titanFinancialTradeService;
    }

    public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
        this.titanJobExecutor = titanJobExecutor;
    }

	public void setTitanFinancialAccountService(
			TitanFinancialAccountService titanFinancialAccountService) {
		this.titanFinancialAccountService = titanFinancialAccountService;
	}



	public TitanFinancialTradeService getTitanFinancialTradeService() {
		return titanFinancialTradeService;
	}



	public TitanFinancialAccountService getTitanFinancialAccountService() {
		return titanFinancialAccountService;
	}



	public ThreadPoolTaskExecutor getTitanJobExecutor() {
		return titanJobExecutor;
	}

	
}
