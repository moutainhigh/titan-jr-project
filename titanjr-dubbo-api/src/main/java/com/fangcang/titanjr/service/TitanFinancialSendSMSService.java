package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.SendRegCodeRequest;
import com.fangcang.titanjr.dto.request.SendSMSRequest;
import com.fangcang.titanjr.dto.response.SendRegCodeResponse;
import com.fangcang.titanjr.dto.response.SendSmsResponse;

public interface TitanFinancialSendSMSService {

	public SendSmsResponse sendSMS(SendSMSRequest SendSMSRequest);
	/**
	 * 发送注册验证码(手机和短信)
	 * @param sendEmailRequest
	 * @return
	 */
	public SendRegCodeResponse sendRegCode(SendRegCodeRequest sendRegCodeRequest);
	
	
}
