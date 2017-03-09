package com.fangcang.titanjr.job.runner;

import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wd on 2016/8/16.
 */
@Service
public class BindBankCardRunner implements JobRunner {

    private static Logger logger = LoggerFactory.getLogger(BindBankCardRunner.class);

    @Autowired
    private TitanFinancialBankCardService titanFinancialBankCardService;

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        try {
            // 业务日志记录
            BizLogger bizLogger = jobContext.getBizLogger();

            // 业务逻辑
            titanFinancialBankCardService.bindBankCardBatch();

            logger.info("我要执行绑定银行卡：" + jobContext);

            // 日志会发送到 LTS (JobTracker上)
            bizLogger.info("业务日志：绑定银行卡测试");
        } catch (Exception e) {
            // 执行失败
            logger.info("Run job failed!", e);
            return new Result(Action.EXECUTE_FAILED, e.getMessage());
        }

        // 执行成功
        return new Result(Action.EXECUTE_SUCCESS, "绑定银行卡成功了");
    }
}
