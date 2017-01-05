package com.fangcang.titanjr.web.servlet;

import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;

public class CityInfoThread  implements Runnable{
	
	private TitanFinancialBaseInfoService titanFinancialBaseInfoService;
	CityInfoThread(TitanFinancialBaseInfoService titanFinancialBaseInfoService){
		this.titanFinancialBaseInfoService = titanFinancialBaseInfoService;
	}

	@Override
	public void run() {
		try {
			titanFinancialBaseInfoService.saveRSCityInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
