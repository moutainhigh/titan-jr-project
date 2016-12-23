package com.fangcang.titanjr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.RefundConfirmRunner;
import com.fangcang.titanjr.job.executors.RepairGDPTransferRunner;
import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;

public class RefundConfirmJob extends QuartzJobBean{

	private TitanFinancialRefundService titanFinancialRefundService;
	
	private ThreadPoolTaskExecutor titanJobExecutor;


	@Override
	 protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {

		System.out.println("调用退款的定时任务开始---");
		RefundConfirmRunner task = new RefundConfirmRunner(titanFinancialRefundService);
        titanJobExecutor.execute(task);
    }


	public ThreadPoolTaskExecutor getTitanJobExecutor() {
		return titanJobExecutor;
	}


	public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
		this.titanJobExecutor = titanJobExecutor;
	}


	public TitanFinancialRefundService getTitanFinancialRefundService() {
		return titanFinancialRefundService;
	}


	public void setTitanFinancialRefundService(
			TitanFinancialRefundService titanFinancialRefundService) {
		this.titanFinancialRefundService = titanFinancialRefundService;
	}
	

}
