package com.fangcang.titanjr.job.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

@Service
public class RefundConfirmRunner implements JobRunner{

	private static Logger logger = LoggerFactory.getLogger(LoanCreditAmountEvaluatingRunner.class);
	
	@Autowired
	private TitanFinancialRefundService titanFinancialRefundService;
	
	
	@Override
	public Result run(JobContext jobContext) throws Throwable {
		  // 业务日志记录
        BizLogger bizLogger = jobContext.getBizLogger();

		System.out.println("");
		titanFinancialRefundService.refundConfirm(null);
		
		logger.info("退款：" + jobContext);
        // 日志会发送到 LTS (JobTracker上)
        bizLogger.info("执行退款查询：贷款测试");
        return new Result(Action.EXECUTE_SUCCESS, "成功");
	}

}
