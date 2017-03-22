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
	 * @deprecated see sendMessage()
	 */
	@Deprecated
	public SendSmsResponse sendSMS(SendSMSRequest SendSMSRequest);
	/**
	 * 发送消息(手机和短信)
	 * @param sendEmailRequest
	 * @return
	 */
	@Deprecated
	public SendCodeResponse sendCode(SendCodeRequest sendCodeRequest);
	/**
	 * 异步发送短信或者邮件
	 * @param sendCodeRequest
	 */
	public void asynSendMessage(SendMessageRequest sendCodeRequest);
	/**
	 * 发送消息(手机和短信)
	 * @param sendEmailRequest
	 * @return
	 */
	public SendMessageResponse sendMessage(SendMessageRequest sendCodeRequest);
	
	
}
