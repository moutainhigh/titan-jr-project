package com.fangcang.titanjr.job.executors;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.LoanAmountEvaluationRequest;
import com.fangcang.titanjr.dto.response.OrganQueryCheckResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;

public class LoanCreditAmountEvaluatingRunner implements Runnable {

	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;

	private TitanFinancialOrganService financialOrganService;

	public LoanCreditAmountEvaluatingRunner(
			TitanFinancialOrganService financialOrganService,
			TitanFinancialLoanCreditService titanFinancialLoanCreditService) {
		this.titanFinancialLoanCreditService = titanFinancialLoanCreditService;
		this.financialOrganService = financialOrganService;
	}

	@Override
	public void run() {

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

					}
				}
			}
		}

	}
}
