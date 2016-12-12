package com.fangcang.titanjr.pay.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.request.NotifyRequest;
import com.fangcang.titanjr.pay.services.listener.TitanCreditServiceListener;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;

@Service("titanCreditService")
public class TitanCreditServiceListenerImpl implements
		TitanCreditServiceListener {
	@Resource
	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;

	@Override
	public void creditSucceed(String orderNo, int status) throws GlobalServiceException {
		NotifyRequest notifyRequest = new NotifyRequest();
		notifyRequest.setBuessNo(orderNo);
		notifyRequest.setStatus(status);
		titanFinancialLoanCreditService.loanCreditNotify(notifyRequest);
		
	}

	@Override
	public void creditFailure(String orderNo, int status, String msg) throws GlobalServiceException {
		NotifyRequest notifyRequest = new NotifyRequest();
		notifyRequest.setBuessNo(orderNo);
		notifyRequest.setStatus(status);
		notifyRequest.setMsg(msg);
		titanFinancialLoanCreditService.loanCreditNotify(notifyRequest);
		
	}
	
}
