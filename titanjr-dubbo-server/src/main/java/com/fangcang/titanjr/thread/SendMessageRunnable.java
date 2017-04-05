package com.fangcang.titanjr.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.dto.response.SendMessageResponse;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;

/**
 * 异步发送信息
 * @author luoqinglong
 * @date   2017年3月1日
 */
public class SendMessageRunnable implements Runnable {
	private static final Log log = LogFactory.getLog(SendMessageRunnable.class);
	
	private SendMessageRequest sendCodeRequest;
	private TitanFinancialSendSMSService sendSMSService;
	
	public SendMessageRunnable(TitanFinancialSendSMSService sendSMSService,SendMessageRequest sendCodeRequest){
		this.sendCodeRequest = sendCodeRequest;
		this.sendSMSService = sendSMSService;
	}
	
	@Override
	public void run() {
		SendMessageResponse sendMessageResponse = this.sendSMSService.sendMessage(sendCodeRequest);
		if(sendMessageResponse.isResult()==false){
			log.error(Tools.getStringBuilder().append("邮件或者短信发送失败,失败信息:").append(sendMessageResponse.getReturnMessage()).append(",发送参数sendCodeRequest：").append(Tools.gsonToString(sendCodeRequest)));
		}
	}

}
