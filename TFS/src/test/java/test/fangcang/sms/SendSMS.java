package test.fangcang.sms;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import test.fangcang.GenericTest;

import com.fangcang.titanjr.dto.request.SendSMSRequest;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;

public class SendSMS extends GenericTest{
	@Resource
	private static TitanFinancialSendSMSService titanFinancialSendSMSService= null;
	

    @Before
    public void init(){
    	titanFinancialSendSMSService = (TitanFinancialSendSMSService)cfx.getBean("titanFinancialSendSMSService");
    }
	
	@Test
	public void testInitCityInfo() throws Exception{
		SendSMSRequest sendSMSRequest = new SendSMSRequest();
		sendSMSRequest.setContent("hello!");
		sendSMSRequest.setMobilePhone("15926148549");
		sendSMSRequest.setProviderkey("3369");
		sendSMSRequest.setMerchantCode("M10000001");
		titanFinancialSendSMSService.sendSMS(sendSMSRequest);
	}
	
}
