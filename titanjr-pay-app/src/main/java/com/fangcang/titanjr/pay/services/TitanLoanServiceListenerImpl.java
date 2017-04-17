package com.fangcang.titanjr.pay.services;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.request.LoanOrderNotifyRequest;
import com.fangcang.titanjr.dto.response.LoanOrderNotifyResponse;
import com.fangcang.titanjr.pay.services.listener.TitanLoanServiceListener;
import com.fangcang.titanjr.service.TitanFinancialLoanService;

@Service("titanLoanService")
public class TitanLoanServiceListenerImpl implements TitanLoanServiceListener {

	private static final Log log = LogFactory
			.getLog(TitanLoanServiceListenerImpl.class);

	@Resource
	private TitanFinancialLoanService loanService;

	@Override
	public boolean loanSucceed(String buessNo, int state) {
		LoanOrderNotifyRequest loanOrderNotifyRequest = new LoanOrderNotifyRequest();
		loanOrderNotifyRequest.setOrderNo(buessNo);
		loanOrderNotifyRequest.setState(state);
		LoanOrderNotifyResponse loanOrderNotifyResponse = loanService
				.loanOrderNotify(loanOrderNotifyRequest);
		if (!loanOrderNotifyResponse.isResult()) {
			log.error("贷款申请通知处理失败,rspmsg:"
					+ loanOrderNotifyResponse.getReturnMessage()
					+ ",泰坦金融贷款订单号buessNo:" + buessNo + ",state:" + state);
			return false;
		}
		return true;
	}

	@Override
	public boolean loanFailure(String buessNo, int state, String msg) {
		LoanOrderNotifyRequest loanOrderNotifyRequest = new LoanOrderNotifyRequest();
		loanOrderNotifyRequest.setOrderNo(buessNo);
		loanOrderNotifyRequest.setState(state);
		loanOrderNotifyRequest.setMsg(msg);
		LoanOrderNotifyResponse loanOrderNotifyResponse = loanService
				.loanOrderNotify(loanOrderNotifyRequest);
		if (!loanOrderNotifyResponse.isResult()) {
			log.error("贷款申请通知处理失败,rspmsg:"
					+ loanOrderNotifyResponse.getReturnMessage()
					+ ",泰坦金融贷款订单号buessNo:" + buessNo + ",state:" + state);
			return false;
		}
		return true;

	}

}
