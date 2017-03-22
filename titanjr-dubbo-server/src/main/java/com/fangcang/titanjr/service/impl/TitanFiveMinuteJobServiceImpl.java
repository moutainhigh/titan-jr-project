package com.fangcang.titanjr.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.ThreadPoolUtil;
import com.fangcang.titanjr.service.TitanCoopService;
import com.fangcang.titanjr.service.TitanFiveMinuteJobService;

@Service("titanFiveMinuteJobService")
public class TitanFiveMinuteJobServiceImpl implements TitanFiveMinuteJobService {
	private static final Log log = LogFactory.getLog(TitanFiveMinuteJobServiceImpl.class);
	@Resource
	private TitanCoopService coopService;
	
	@Override
	public void runLessTimeAsynTask() {
		log.info("开始执行定时任务方法runLessTimeAsynTask()");
		//异步线程执行
		ThreadPoolUtil.excute(new Runnable() {
			@Override
			public void run() {
				coopService.notifyCoopOrgInfo();
			}
		});
		
	}

}
