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
//1.为什么只有spring注入的对象，才适用于事物？spring封装了mybatis的事物是封装jdbc。那这样的话想用spring上下文的资源，就必须纳入spring的容器。
    
   
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
