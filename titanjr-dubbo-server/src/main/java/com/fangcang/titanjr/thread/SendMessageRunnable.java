package com.fangcang.titanjr.thread;

import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;

/**
 * 异步发送信息
 * @author luoqinglong
 * @date   2017年3月1日
 */
public class SendMessageRunnable implements Runnable {
	
	private SendMessageRequest sendCodeRequest;
	private TitanFinancialSendSMSService sendSMSService;
	
	public SendMessageRunnable(TitanFinancialSendSMSService sendSMSService,SendMessageRequest sendCodeRequest){
		this.sendCodeRequest = sendCodeRequest;
		this.sendSMSService = sendSMSService;
	}
	
	@Override
	public void run() {
		this.sendSMSService.sendMessage(sendCodeRequest);
	}

}
