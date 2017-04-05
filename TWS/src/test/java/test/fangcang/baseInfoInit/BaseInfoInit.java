package test.fangcang.baseInfoInit;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import test.fangcang.GenericTest;

import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;

public class BaseInfoInit extends GenericTest{
	@Resource
	private static TitanFinancialBaseInfoService titanFinancialBaseInfoService= null;
	

    @Before
    public void init(){
    	titanFinancialBaseInfoService = (TitanFinancialBaseInfoService)cfx.getBean("titanFinancialBaseInfoService");
    }
	
//	@Test
	public void testInitCityInfo() throws Exception{
		titanFinancialBaseInfoService.saveRSCityInfo();
	}
	
	@Test
	public void testInitBankInfo()throws Exception{
		titanFinancialBaseInfoService.saveRSBankInfo();
	}
	
	
}
