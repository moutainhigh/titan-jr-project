package com.fangcang.titanjr.job.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.request.RepairTransferRequest;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
@Service
public class RepairGDPTransferRunner implements JobRunner{

	 private static Logger logger = LoggerFactory.getLogger(RepairGDPTransferRunner.class);

	    @Autowired
	    private TitanFinancialTradeService titanFinancialTradeService;

	    @Override
	    public Result run(JobContext jobContext) throws Throwable {
	        try {
	            // 业务日志记录
	            BizLogger bizLogger = jobContext.getBizLogger();

	            // 业务逻辑
	            RepairTransferRequest rep = new RepairTransferRequest();
	            titanFinancialTradeService.repairTransferOrder(rep);

	            logger.info("GDP修复：" + jobContext);

	            // 日志会发送到 LTS (JobTracker上)
	            bizLogger.info("业务日志：GDP修复测试");
	        } catch (Exception e) {
	            // 执行失败
	            logger.info("Run job failed!", e);
	            return new Result(Action.EXECUTE_FAILED, e.getMessage());
	        }

	        // 执行成功
	        return new Result(Action.EXECUTE_SUCCESS, "GDP修复");
	    }
}
