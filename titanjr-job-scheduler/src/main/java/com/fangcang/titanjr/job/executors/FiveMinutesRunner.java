package com.fangcang.titanjr.job.executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.service.TitanFiveMinuteJobService;

/**
 * 5分钟间隔任务
 * @author luoqinglong
 * @date   2017年3月2日
 */
public class FiveMinutesRunner implements Runnable {
	private static final Log log = LogFactory.getLog(FiveMinutesRunner.class);
	private TitanFiveMinuteJobService titanFiveMinuteJobService;
	
	public FiveMinutesRunner(TitanFiveMinuteJobService titanFiveMinuteJobService){
		this.titanFiveMinuteJobService = titanFiveMinuteJobService;
	}
	
	@Override
	public void run() {
		log.info("开始执行5分钟间隔的定时任务:titanFiveMinuteJobService.runLessTimeAsynTask()");
		titanFiveMinuteJobService.runLessTimeAsynTask();
	}

}
