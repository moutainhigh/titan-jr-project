package com.fangcang.titanjr.pay.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.request.AgreementConfirmRequest;
import com.fangcang.titanjr.dto.request.NotifyRequest;
import com.fangcang.titanjr.pay.services.listener.TitanCreditServiceListener;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;

@Service("titanCreditService")
public class TitanCreditServiceListenerImpl implements
		TitanCreditServiceListener {
	@Resource
	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;

	@Override
	public void creditSucceed(String orderNo, String buessNo, int status) throws GlobalServiceException {
		NotifyRequest notifyRequest = new NotifyRequest();
		notifyRequest.setOrderNo(orderNo);
		notifyRequest.setBuessNo(buessNo);
		notifyRequest.setStatus(status);
		titanFinancialLoanCreditService.loanCreditNotify(notifyRequest);
		
	}

	@Override
	public void creditFailure(String orderNo, String buessNo, int status, String msg) throws GlobalServiceException {
		NotifyRequest notifyRequest = new NotifyRequest();
		notifyRequest.setOrderNo(orderNo);
		notifyRequest.setBuessNo(buessNo);
		notifyRequest.setStatus(status);
		notifyRequest.setMsg(msg);
		titanFinancialLoanCreditService.loanCreditNotify(notifyRequest);
		
	}

	@Override
	public void agreementConfirm(String buessNo) {
		AgreementConfirmRequest agreementConfirmRequest = new AgreementConfirmRequest();
		agreementConfirmRequest.setBuessNo(buessNo);
		titanFinancialLoanCreditService.agreementConfirm(agreementConfirmRequest);
		
	}
	
}
