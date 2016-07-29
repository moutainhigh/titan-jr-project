package test.fangcang.titanjr.service;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.dto.request.SendCodeRequest;
import com.fangcang.titanjr.dto.request.SendSMSRequest;
import com.fangcang.titanjr.dto.response.SendCodeResponse;
import com.fangcang.titanjr.dto.response.SendSmsResponse;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;

/**
 * Created by zhaoshan on 2016/5/10.
 */
public class TitanFinancialSendSMSServiceTest extends SpringTest  {

    @Resource
    TitanFinancialSendSMSService titanFinancialSendSMSService;

   // @Test
    public void testSendMsg(){
    	try{
	    	SendSMSRequest sendSMSRequest = new SendSMSRequest();
			sendSMSRequest.setContent("hello!");
			sendSMSRequest.setMobilePhone("13352989767");
			sendSMSRequest.setProviderkey("3369");
			sendSMSRequest.setMerchantCode("M10021071");
			SendSmsResponse response = titanFinancialSendSMSService.sendSMS(sendSMSRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
		Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
    }
    
    @Test
    public void testSendEmail(){
    	try{
    		SendCodeRequest sendRegCodeRequest = new SendCodeRequest();
    		sendRegCodeRequest.setContent("验证码123456");
    		sendRegCodeRequest.setSubject("主题455");
    		sendRegCodeRequest.setMerchantCode("M10000181");
    		sendRegCodeRequest.setReceiveAddress("2637799268@qq.com");
    		SendCodeResponse response = titanFinancialSendSMSService.sendCode(sendRegCodeRequest);
	    	System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertFalse(1==1);
			}
	}
}
