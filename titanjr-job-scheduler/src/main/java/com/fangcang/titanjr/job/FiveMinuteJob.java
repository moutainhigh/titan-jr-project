package com.fangcang.titanjr.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.quartz.QuartzJobBean;
import com.fangcang.titanjr.job.executors.FiveMinutesRunner;
import com.fangcang.titanjr.service.TitanFiveMinuteJobService;

public class FiveMinuteJob extends QuartzJobBean{
	private static final Log log = LogFactory.getLog(FiveMinuteJob.class);
	private TitanFiveMinuteJobService titanFiveMinuteJobService;
	private ThreadPoolTaskExecutor titanJobExecutor;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		log.info("开始执行5分钟间隔的定时任务job方法:FiveMinutesRunner > titanFiveMinuteJobService");
		titanJobExecutor.execute(new FiveMinutesRunner(titanFiveMinuteJobService));
	}


	public void setTitanFiveMinuteJobService(
			TitanFiveMinuteJobService titanFiveMinuteJobService) {
		this.titanFiveMinuteJobService = titanFiveMinuteJobService;
	}

	public void setTitanJobExecutor(ThreadPoolTaskExecutor titanJobExecutor) {
		this.titanJobExecutor = titanJobExecutor;
	}
	
}
