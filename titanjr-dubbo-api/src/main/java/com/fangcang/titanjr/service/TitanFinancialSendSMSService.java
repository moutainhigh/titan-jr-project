package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.SendCodeRequest;
import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.dto.request.SendSMSRequest;
import com.fangcang.titanjr.dto.response.SendCodeResponse;
import com.fangcang.titanjr.dto.response.SendMessageResponse;
import com.fangcang.titanjr.dto.response.SendSmsResponse;

public interface TitanFinancialSendSMSService {

	/**
	 * 发送短信
	 * @param SendSMSRequest
	 * @return
	 */
	public SendSmsResponse sendSMS(SendSMSRequest SendSMSRequest);
	/**
	 * 发送消息(手机和短信)
	 * @param sendEmailRequest
	 * @return
	 */
	@Deprecated
	public SendCodeResponse sendCode(SendCodeRequest sendCodeRequest);
	/**
	 * 发送消息(手机和短信)
	 * @param sendEmailRequest
	 * @return
	 */
	public SendMessageResponse sendMessage(SendMessageRequest sendCodeRequest);
	
	
}
