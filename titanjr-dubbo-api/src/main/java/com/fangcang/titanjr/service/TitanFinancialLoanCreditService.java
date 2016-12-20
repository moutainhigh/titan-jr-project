package com.fangcang.titanjr.service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.request.AgreementConfirmRequest;
import com.fangcang.titanjr.dto.request.ApplyLoanCreditRequest;
import com.fangcang.titanjr.dto.request.AuditCreidtOrderRequest;
import com.fangcang.titanjr.dto.request.GetAuditEvaluationRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.request.NotifyRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.request.SynLoanCreditOrderRequest;
import com.fangcang.titanjr.dto.response.AgreementConfirmResponse;
import com.fangcang.titanjr.dto.response.ApplyLoanCreditResponse;
import com.fangcang.titanjr.dto.response.AuditCreidtOrderResponse;
import com.fangcang.titanjr.dto.response.GetAuditEvaluationResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.GetCreditOrderCountResponse;
import com.fangcang.titanjr.dto.response.LoanCreditSaveResponse;
import com.fangcang.titanjr.dto.response.NotifyResponse;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.dto.response.SynLoanCreditOrderResponse;

/**
 * 授信业务接口定义
 * 
 * @author wengxitao
 *
 */
public interface TitanFinancialLoanCreditService {

	/**
	 * 审核贷款授信单
	 * 
	 * @param 审核对象
	 */
	public AuditCreidtOrderResponse auditCreditOrder(AuditCreidtOrderRequest req);

	/**
	 * 获取授信单的审核评价信息
	 * 
	 * @return
	 */
	public GetAuditEvaluationResponse getAuditEvaluationInfo(
			GetAuditEvaluationRequest req);

	/**
	 * 获取授信信息
	 * 
	 * @param req
	 *            请求对象
	 * @return
	 */
	public GetCreditInfoResponse getCreditOrderInfo(GetCreditInfoRequest req);

	/**
	 * 查询授信申请公司信息
	 * 
	 * @return
	 */
	public PageCreditCompanyInfoResponse queryPageCreditCompanyInfo(
			QueryPageCreditCompanyInfoRequest req);

	/**
	 * 保存授信单信息 1.判定orderNo，若为空，则生成编码并保存，不为空，则更新
	 * 2.LoanCreditCompany中的controllDatas做了调整，方便存储；
	 * 
	 * @param req
	 *            请求对象
	 * @return
	 */
	public LoanCreditSaveResponse saveCreditOrder(LoanCreditSaveRequest req);

	/**
	 * 授信申请
	 * 
	 * @param req
	 *            授信对象
	 * @return
	 */
	public ApplyLoanCreditResponse applyLoanCredit(ApplyLoanCreditRequest req);

	/**
	 * 授信申请数量
	 * 
	 * @param request
	 * @return
	 */
	public GetCreditOrderCountResponse getCreditOrderCount(
			GetCreditOrderCountRequest request);

	/**
	 * 授信申请结果通知
	 * 
	 * @param notifyRequest
	 * @return
	 * @throws GlobalServiceException
	 */
	public NotifyResponse loanCreditNotify(NotifyRequest notifyRequest)
			throws GlobalServiceException;

	/**
	 * 协议确认
	 * 
	 * @param agreementConfirmRequest
	 * @return
	 */
	public AgreementConfirmResponse agreementConfirm(
			AgreementConfirmRequest agreementConfirmRequest);

	/**
	 * 同步授信单号
	 * 
	 * @param creditOrderRequest
	 * @return
	 */
	public SynLoanCreditOrderResponse synLoanCreditOrder(
			SynLoanCreditOrderRequest creditOrderRequest);
}
