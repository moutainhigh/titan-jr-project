package com.fangcang.titanjr.job.runner;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.service.TitanFixService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

@Service
public class NotifyTradeStatusRunner implements JobRunner {

private static Logger logger = LoggerFactory.getLogger(NotifyTradeStatusRunner.class);
    
    @Resource
    private TitanFixService titanFixService;
	
 
    @Override
    public Result run(JobContext jobContext) throws Throwable {
        try {
            // 业务日志记录
            BizLogger bizLogger = jobContext.getBizLogger();

            // 业务逻辑
            titanFixService.notifyTradeStatus();

            logger.info("执行定时任务[通知第三方状态]：notifyTradeStatus，jobContext：" + jobContext);

            // 日志会发送到 LTS (JobTracker上)
            bizLogger.info("业务日志：执行定时任务[通知第三方状态]:notifyTradeStatus");
        } catch (Exception e) {
            // 执行失败
            logger.info("Run job failed!", e);
            return new Result(Action.EXECUTE_FAILED, e.getMessage());
        }
        // 执行成功
        return new Result(Action.EXECUTE_SUCCESS, "定时任务[notifyTradeStatus]执行成功");
    }
	
}
