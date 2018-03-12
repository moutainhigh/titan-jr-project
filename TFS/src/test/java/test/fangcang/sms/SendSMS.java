package test.fangcang.sms;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;

import test.fangcang.GenericTest;

public class SendSMS extends GenericTest{
	@Resource
	private static TitanFinancialSendSMSService titanFinancialSendSMSService= null;
	

    @Before
    public void init(){
    	titanFinancialSendSMSService = (TitanFinancialSendSMSService)cfx.getBean("titanFinancialSendSMSService");
    }
	
	@Test
	public void testInitCityInfo() throws Exception{
		SendMessageRequest sendSMSRequest = new SendMessageRequest();
		sendSMSRequest.setContent("hello!");
		sendSMSRequest.setReceiveAddress("15926148549");
		sendSMSRequest.setProviderkey("3369");
		sendSMSRequest.setMerchantCode("M10000001");
		titanFinancialSendSMSService.asynSendMessage(sendSMSRequest);
	}
	
}
