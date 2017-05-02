package com.fangcang.titanjr.job.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.service.TitanCoopService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

/**
 */
@Service
public class NotifyCoopOrgInfoRunner implements JobRunner {

    private static Logger logger = LoggerFactory.getLogger(NotifyCoopOrgInfoRunner.class);
    
    @Autowired
    private TitanCoopService titanCoopService;
	
 
    @Override
    public Result run(JobContext jobContext) throws Throwable {
        try {
            // 业务日志记录
            BizLogger bizLogger = jobContext.getBizLogger();

            // 业务逻辑
            titanCoopService.notifyCoopOrgInfo();

            logger.info("执行定时任务[短时间任务]：notifyCoopOrgInfo，jobContext：" + jobContext);

            // 日志会发送到 LTS (JobTracker上)
            bizLogger.info("业务日志：执行定时任务[短时间任务]:notifyCoopOrgInfo");
        } catch (Exception e) {
            // 执行失败
            logger.info("Run job failed!", e);
            return new Result(Action.EXECUTE_FAILED, e.getMessage());
        }
        // 执行成功
        return new Result(Action.EXECUTE_SUCCESS, "定时任务notifyCoopOrgInfo运行成功");
    }
}
