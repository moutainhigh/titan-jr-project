package com.fangcang.titanjr.job.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.LoanAmountEvaluationRequest;
import com.fangcang.titanjr.dto.response.OrganQueryCheckResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

@Service
public class LoanCreditAmountEvaluatingRunner implements JobRunner{
	
	private static Logger logger = LoggerFactory.getLogger(LoanCreditAmountEvaluatingRunner.class);
	@Autowired
	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;
	@Autowired
	private TitanFinancialOrganService financialOrganService;
	
	@Override
	public Result run(JobContext jobContext) throws Throwable {
		
		
		  // 业务日志记录
        BizLogger bizLogger = jobContext.getBizLogger();

        System.out.println("授信金额");
        
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setCurrentPage(1);
		organQueryRequest.setUserType(1);

		OrganQueryCheckResponse checkResponse = financialOrganService
				.queryFinancialOrganForPage(organQueryRequest);

		if (checkResponse != null
				&& checkResponse.getPaginationSupport() != null) {
			PaginationSupport<OrgCheckDTO> paginationSupport = checkResponse
					.getPaginationSupport();

			List<OrgCheckDTO> checkDTOs = paginationSupport.getItemList();

			LoanAmountEvaluationRequest request = new LoanAmountEvaluationRequest();
			for (int j = 1; j <= paginationSupport.getTotalPage(); j++) {
				if (j > 1) {
					organQueryRequest.setCurrentPage(j);
					checkResponse = financialOrganService
							.queryFinancialOrganForPage(organQueryRequest);
					paginationSupport = checkResponse.getPaginationSupport();
					checkDTOs = paginationSupport.getItemList();
				}

				for (int i = 0; i < checkDTOs.size(); i++) {
					request.setOrgCode(checkDTOs.get(i).getOrgcode());
					try {
						titanFinancialLoanCreditService
								.loanAmountEvaluation(request);
					} catch (Exception ex) {
						 return new Result(Action.EXECUTE_FAILED, "失败");
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
