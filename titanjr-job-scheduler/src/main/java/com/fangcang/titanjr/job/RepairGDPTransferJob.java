package com.fangcang.titanjr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.RepairGDPTransferRunner;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;

public class RepairGDPTransferJob extends QuartzJobBean {

	private TitanFinancialTradeService titanFinancialTradeService;
	
	private ThreadPoolTaskExecutor titanJobExecutor;


	@Override
	 protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {

		System.out.println("调用GDP的定时任务开始---");
		RepairGDPTransferRunner task = new RepairGDPTransferRunner(titanFinancialTradeService);
        titanJobExecutor.execute(task);
    }


	public TitanFinancialTradeService getTitanFinancialTradeService() {
		return titanFinancialTradeService;
	}


	public void setTitanFinancialTradeService(
			TitanFinancialTradeService titanFinancialTradeService) {
		this.titanFinancialTradeService = titanFinancialTradeService;
	}


	public ThreadPoolTaskExecutor getTitanJobExecutor() {
		return titanJobExecutor;
	}


	public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
		this.titanJobExecutor = titanJobExecutor;
	}

}
