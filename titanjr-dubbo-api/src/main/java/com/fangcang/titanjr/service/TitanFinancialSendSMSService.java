package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.SendCodeRequest;
import com.fangcang.titanjr.dto.request.SendSMSRequest;
import com.fangcang.titanjr.dto.response.SendCodeResponse;
import com.fangcang.titanjr.dto.response.SendSmsResponse;

public interface TitanFinancialSendSMSService {

	public SendSmsResponse sendSMS(SendSMSRequest SendSMSRequest);
	/**
	 * 发送验证码(手机和短信)
	 * @param sendEmailRequest
	 * @return
	 */
	public SendCodeResponse sendCode(SendCodeRequest sendCodeRequest);
	
	
}
