package com.fangcang.titanjr.job.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
@Service
public class UnFreezeTransOrderRunner implements JobRunner {
	  private static Logger logger = LoggerFactory.getLogger(UnFreezeTransOrderRunner.class);

	    @Autowired
		private TitanFinancialAccountService titanFinancialAccountService;

	    @Override
	    public Result run(JobContext jobContext) throws Throwable {
	        try {
	            // 业务日志记录
	            BizLogger bizLogger = jobContext.getBizLogger();

	            // 业务逻辑
	            this.unFreezeAllTransOrder();

	            logger.info("我要执行解冻操作：" + jobContext);

	            // 日志会发送到 LTS (JobTracker上)
	            bizLogger.info("业务日志：解冻操作测试");
	        } catch (Exception e) {
	            // 执行失败
	            logger.info("Run job failed!", e);
	            return new Result(Action.EXECUTE_FAILED, e.getMessage());
	        }

	        // 执行成功
	        return new Result(Action.EXECUTE_SUCCESS, "解冻操作执行成功了");
	    }
	    
	    private void unFreezeAllTransOrder(){
			try {
				int offset=0;
				int row =100;
				do{
					System.out.println("开始执行定时:"+Thread.currentThread().getName());
					 row = titanFinancialAccountService.unFreezeOrder(offset,row);
					 offset = (offset+1)*row;
				}while(row==100);
				
			} catch (Exception e) {
			}
		}
}
