package com.fangcang.titanjr.job.executors;

import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;

public class BindBankCardRunner implements Runnable{

    private TitanFinancialBankCardService titanFinancialBankCardService;
	
	public BindBankCardRunner(TitanFinancialBankCardService titanFinancialBankCardService){
		this.titanFinancialBankCardService = titanFinancialBankCardService;
	}
	
	@Override
	public void run() {
		titanFinancialBankCardService.bindBankCardBatch();
	}
	
	
}
