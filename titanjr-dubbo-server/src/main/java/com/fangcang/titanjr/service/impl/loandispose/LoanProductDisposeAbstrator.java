package com.fangcang.titanjr.service.impl.loandispose;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;

/**
 * 贷款逻辑抽象定义
 * 
 * @ClassName: LoanProductDisposeAbstrator
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月14日 下午3:07:52
 */
public abstract class LoanProductDisposeAbstrator {

	protected static final Log log = LogFactory
			.getLog(LoanProductDisposeAbstrator.class);
	/**
	 * 申请贷款
	 * @Title: applyLoan 
	 * @Description: TODO
	 * @param request
	 * @param req
	 * @return
	 * @throws com.fangcang.titanjr.exception.ServiceException
	 * @return: ApplyLoanResponse
	 */
	public abstract ApplyLoanResponse applyLoan(NewLoanApplyRequest request,
			ApplyLoanRequest req)
			throws com.fangcang.titanjr.exception.ServiceException;

	/**
	 * 贷款审核失败
	 * @Title: loanFailNotify 
	 * @Description: TODO
	 * @param orderNo
	 * @param status
	 * @param msg 失败原因
	 * @return: void
	 */
	public abstract void loanFailNotify(String orderNo,
			LoanOrderStatusEnum status , String msg);
	/**
	 * 贷款审核通过
	 * @Title: loanAuditPassNotify 
	 * @Description: TODO
	 * @param orderNo
	 * @param status
	 * @return: void
	 */
	public abstract void loanAuditPassNotify(String  orderNo,
			LoanOrderStatusEnum status);
	/**
	 * 放款成功
	 * @Title: loanSuccessNotify 
	 * @Description: TODO
	 * @param orderNo
	 * @param status 状态
	 * @return: void
	 */
	public abstract void loanSuccessNotify(String orderNo,
			LoanOrderStatusEnum status);

}
