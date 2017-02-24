package com.fangcang.titanjr.job.executors;

import java.util.List;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.SynLoanOrderRequest;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;

public class LoanOrderSynRunner implements Runnable {

	private TitanFinancialLoanService titanFinancialLoanService;

	public LoanOrderSynRunner(
			TitanFinancialLoanService titanFinancialLoanService) {
		this.titanFinancialLoanService = titanFinancialLoanService;
	}

	@Override
	public void run() {

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

					}
				}
			}
		}
	}

}
