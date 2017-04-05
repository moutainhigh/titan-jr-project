package com.fangcang.titanjr.service.impl.loandispose;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.exception.ServiceException;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;
import com.fangcang.titanjr.rs.response.NewLoanApplyResponse;

/**
 * 包房贷款逻辑处理
 * 
 * @ClassName: LoanRoomPackDispose
 * @Description: TODO
 * @author: wengxitao
 * @date: 2017年3月14日 下午3:06:11
 */
@Component("loanRoomPackDispose")
public class LoanRoomPackDispose extends LoanProductDisposeAbstrator {

	@Resource
	protected RSCreditManager rsCreditManager;
	
	@Resource
	private LoanOrderDao loanOrderDao;

	@Override
	public ApplyLoanResponse applyLoan(NewLoanApplyRequest request,
			ApplyLoanRequest req) throws ServiceException {

		ApplyLoanResponse response = new ApplyLoanResponse();

		NewLoanApplyResponse loanResponse = rsCreditManager
				.newLoanApply(request);

		if (loanResponse == null || (!loanResponse.isSuccess())) {
			log.error(loanResponse.getReturnCode() + ":"
					+ loanResponse.getReturnMsg());
			throw new ServiceException(loanResponse.getReturnCode() + ":"
					+ loanResponse.getReturnMsg());
		}

		response.setOrderNo(loanResponse.getOrderid());
		response.putSuccess();
		return response;
	}

	@Override
	public void loanFailNotify(String orderNo, LoanOrderStatusEnum status , String msg) {
		// 设置贷款单的状态为贷款失败
		LoanApplyOrder loanOrder = new LoanApplyOrder();
		loanOrder.setOrderNo(orderNo);
		loanOrder.setStatus(status.getKey());
		loanOrder.setErrorMsg(msg);
		loanOrderDao.updateLoanApplyOrder(loanOrder);
	}

	@Override
	public void loanSuccessNotify(String orderNo, LoanOrderStatusEnum status) {
		LoanApplyOrder loanOrder = new LoanApplyOrder();
		loanOrder.setOrderNo(orderNo);
		loanOrder.setStatus(status.getKey());
		loanOrderDao.updateLoanApplyOrder(loanOrder);
	}

	@Override
	public void loanAuditPassNotify(String orderNo, LoanOrderStatusEnum status) {
		LoanApplyOrder loanOrder = new LoanApplyOrder();
		loanOrder.setOrderNo(orderNo);
		loanOrder.setStatus(status.getKey());
		loanOrderDao.updateLoanApplyOrder(loanOrder);
	}

}
