package com.fangcang.titanjr.job.executors;

import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;

public class RefundConfirmRunner implements Runnable{
	
	private TitanFinancialRefundService titanFinancialRefundService;

	public RefundConfirmRunner(TitanFinancialRefundService titanFinancialRefundService){
		this.titanFinancialRefundService = titanFinancialRefundService;
	};
	
	@Override
	public void run() {
		System.out.println("执行退款查询");
		titanFinancialRefundService.refundConfirm(null);
	}

	
}
