package com.fangcang.titanjr.job.executors;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.request.SynLoanCreditOrderRequest;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;

public class LoanCreditSynRunner implements Runnable {

	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;

	public LoanCreditSynRunner(
			TitanFinancialLoanCreditService titanFinancialLoanCreditService) {
		this.titanFinancialLoanCreditService = titanFinancialLoanCreditService;
	}

	@Override
	public void run() {

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
					} catch (Exception ex) {

					}
				}
			}
		}
	}
}
