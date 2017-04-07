package com.fangcang.titanjr.job.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.SynLoanOrderRequest;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

@Service
public class LoanOrderSynRunner implements JobRunner{
	
	private static Logger logger = LoggerFactory.getLogger(LoanCreditAmountEvaluatingRunner.class);
	
	@Autowired
	private TitanFinancialLoanService titanFinancialLoanService;

	@Override
	public Result run(JobContext jobContext) throws Throwable {
		
		// 业务日志记录
        BizLogger bizLogger = jobContext.getBizLogger();
		
		GetLoanOrderInfoListRequest req = new GetLoanOrderInfoListRequest();
		req.setCurrentPage(1);
		
		req.setOrderStatusEnum(LoanOrderStatusEnum.HAVE_LOAN,
				LoanOrderStatusEnum.LOAN_EXPIRY,
				LoanOrderStatusEnum.LOAN_REQ_ING,
				LoanOrderStatusEnum.WAIT_AUDIT);

		GetLoanOrderInfoListResponse listResponse = titanFinancialLoanService
				.getLoanOrderInfoList(req);

		if (listResponse != null && listResponse.getApplyOrderInfo() != null) {

			List<LoanApplyOrderBean> applyOrderBeans = listResponse
					.getApplyOrderInfo();
			SynLoanOrderRequest sreq = new SynLoanOrderRequest();
			for (int j = 1; j <= listResponse.getTotalPage(); j++) {
				if (j > 1) {
					req.setCurrentPage(j);
					listResponse = titanFinancialLoanService
							.getLoanOrderInfoList(req);
					applyOrderBeans = listResponse.getApplyOrderInfo();

				}
				for (int i = 0; i < applyOrderBeans.size(); i++) {
					sreq.setOrderNo(applyOrderBeans.get(i).getOrderNo());
					sreq.setOrgCode(applyOrderBeans.get(i).getOrgCode());
					try {
						titanFinancialLoanService.synLoanOrderInfo(sreq);
					} catch (Exception ex) {
//						 return new Result(Action.EXECUTE_FAILED, "成功");
					}
				}
			}
		}
		logger.info("贷款：" + jobContext);
        // 日志会发送到 LTS (JobTracker上)
        bizLogger.info("业务日志：贷款测试");
        return new Result(Action.EXECUTE_SUCCESS, "成功");
	}

}
