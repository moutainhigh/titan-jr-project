package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.request.CancelLoanRequest;
import com.fangcang.titanjr.dto.request.GetHistoryRepaymentListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.request.GetOrgLoanStatInfoRequest;
import com.fangcang.titanjr.dto.request.RepaymentLoanRequest;
import com.fangcang.titanjr.dto.request.SaveLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.dto.response.CancelLoanResponse;
import com.fangcang.titanjr.dto.response.GetHistoryRepaymentListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.GetOrgLoanStatInfoResponse;
import com.fangcang.titanjr.dto.response.RepaymentLoanResponse;
import com.fangcang.titanjr.dto.response.SaveLoanOrderInfoResponse;

/**
 * 贷款业务接口定义
 * 
 * @author wengxitao
 *
 */
public interface TitanFinancialLoanService {
	/**
	 * 贷款申请
	 * 
	 * @param req
	 * @return
	 */
	public ApplyLoanResponse applyLoan(ApplyLoanRequest req) throws Exception;

	/**
	 * 取消贷款请求
	 * 
	 * @param req
	 * @return
	 */
	public CancelLoanResponse cancelLoan(CancelLoanRequest req);

	/**
	 * 主动还贷款
	 * 
	 * @param req
	 * @return
	 */
	public RepaymentLoanResponse repaymentLoan(RepaymentLoanRequest req);

	/**
	 * 查询单个贷款信息
	 * 
	 * @param req
	 * @return
	 */
	public GetLoanOrderInfoResponse getLoanOrderInfo(GetLoanOrderInfoRequest req);

	/**
	 * 查询贷款列表
	 * 
	 * @param req
	 * @return
	 */
	public GetLoanOrderInfoListResponse getLoanOrderInfoList(
			GetLoanOrderInfoListRequest req);
	/**
	 * 根據組織機構ID查詢對於的貸款統計信息
	 * @param req
	 * @return
	 */
	public GetOrgLoanStatInfoResponse getOrgLoanStatInfo(
			GetOrgLoanStatInfoRequest req);
	
	
	/**
	 *查尋還款歷史列表
	 */
	public GetHistoryRepaymentListResponse getHistoryRepaymentList(
			GetHistoryRepaymentListRequest req);

	/**
	 * 保存贷款单信息
	 * 
	 * @param req
	 * @return
	 */
	public SaveLoanOrderInfoResponse saveLoanOrderInfo(
			SaveLoanOrderInfoRequest req);
}
