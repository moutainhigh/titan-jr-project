package com.fangcang.titanjr.job.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.request.SynLoanCreditOrderRequest;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

@Service
public class LoanCreditSynRunner implements JobRunner {

	private static Logger logger = LoggerFactory.getLogger(LoanCreditSynRunner.class);
	
    @Autowired
    private TitanFinancialLoanCreditService titanFinancialLoanCreditService;
	
	@Override
	public Result run(JobContext jobContext) throws Throwable {

		  // 业务日志记录
        BizLogger bizLogger = jobContext.getBizLogger();
        
        System.out.println("授信");
        
		QueryPageCreditCompanyInfoRequest req = new QueryPageCreditCompanyInfoRequest();
		req.setStatus(LoanCreditStatusEnum.REVIEW_PASS.getStatus());
		req.setCurrentPage(1);

		PageCreditCompanyInfoResponse infoResponse = titanFinancialLoanCreditService
				.queryPageCreditCompanyInfo(req);

		if (infoResponse != null
				&& infoResponse.getPageCreditCompanyInfoDTO() != null) {

			PaginationSupport<CreditCompanyInfoDTO> paginationSupports = infoResponse
					.getPageCreditCompanyInfoDTO();

			List<CreditCompanyInfoDTO> companyInfoDTOs = paginationSupports
					.getItemList();
			
			SynLoanCreditOrderRequest creditOrderRequest = new SynLoanCreditOrderRequest();

			for (int j = 1; j <= paginationSupports.getTotalPage(); j++) {

				if (j > 1) {
					req.setCurrentPage(j);
					
					infoResponse = titanFinancialLoanCreditService
							.queryPageCreditCompanyInfo(req);
					
					paginationSupports = infoResponse
							.getPageCreditCompanyInfoDTO();
					
					companyInfoDTOs = paginationSupports.getItemList();
				}

				for (int i = 0; i < companyInfoDTOs.size(); i++) {
					creditOrderRequest.setOrderNo(companyInfoDTOs.get(i)
							.getOrderNo());
					creditOrderRequest.setOrgCode(companyInfoDTOs.get(i)
							.getOrgCode());
					try {

						titanFinancialLoanCreditService
								.synLoanCreditOrder(creditOrderRequest);
						logger.info("贷款授信：" + jobContext);
				        // 日志会发送到 LTS (JobTracker上)
				        bizLogger.info("业务日志：贷款授信");
					} catch (Exception ex) {
						 return new Result(Action.EXECUTE_FAILED, "成功");
					}
				}
			}
		}
		 return new Result(Action.EXECUTE_SUCCESS, "成功");
	}

}
