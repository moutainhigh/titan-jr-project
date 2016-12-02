package com.fangcang.titanjr.web.servlet;

import java.io.IOException;
import java.nio.CharBuffer;

import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;

public class BankInfoThread implements Runnable{
	
	private TitanFinancialBaseInfoService titanFinancialBaseInfoService;
	
	BankInfoThread(TitanFinancialBaseInfoService titanFinancialBaseInfoService){
		this.titanFinancialBaseInfoService = titanFinancialBaseInfoService;
	}

	@Override
	public void run() {
		try {
			titanFinancialBaseInfoService.saveRSBankInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
