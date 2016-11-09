package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.QueryBorrowinfoRequest;
import com.fangcang.titanjr.rs.request.QueryUserInitiativeRepaymentRequest;
import com.fangcang.titanjr.rs.request.QueryUserRepaymentRequest;
import com.fangcang.titanjr.rs.request.UserInitiativeRepamentRequest;
import com.fangcang.titanjr.rs.request.OprsystemCreditCompanyRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditapplicationRequest;
import com.fangcang.titanjr.rs.request.QueryCreditMerchantInfoRequest;
import com.fangcang.titanjr.rs.request.QueryLoanApplyRequest;
import com.fangcang.titanjr.rs.request.StopLoanRequest;
import com.fangcang.titanjr.rs.request.OrderServiceAgreementConfirmRequest;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;
import com.fangcang.titanjr.rs.response.QueryBorrowinfoResponse;
import com.fangcang.titanjr.rs.response.QueryUserInitiativeRepaymentResponse;
import com.fangcang.titanjr.rs.response.QueryUserRepaymentResponse;
import com.fangcang.titanjr.rs.response.UserInitiativeRepamentResponse;
import com.fangcang.titanjr.rs.response.OprsystemCreditCompanyResponse;
import com.fangcang.titanjr.rs.response.OrderMixserviceCreditapplicationResponse;
import com.fangcang.titanjr.rs.response.QueryCreditMerchantInfoResponse;
import com.fangcang.titanjr.rs.response.QueryLoanApplyResponse;
import com.fangcang.titanjr.rs.response.StopLoanResponse;
import com.fangcang.titanjr.rs.response.OrderServiceAgreementConfirmResponse;
import com.fangcang.titanjr.rs.response.NewLoanApplyResponse;

/**
 * 信贷业务
 * @author luoqinglong
 * @2016年10月31日
 */
public interface RSCreditManager {
	
	/**
	 * 上报企业资料信息
	 * @param request
	 * @return
	 */
	OprsystemCreditCompanyResponse oprsystemCreditCompany(OprsystemCreditCompanyRequest request);
	
	/**
	 * 申请授信
	 * @param request
	 * @return
	 */
	OrderMixserviceCreditapplicationResponse orderMixserviceCreditapplication(OrderMixserviceCreditapplicationRequest request);
	
	/**
	 * 协议确认
	 * @param request
	 * @return
	 */
	OrderServiceAgreementConfirmResponse agreementConfirm(OrderServiceAgreementConfirmRequest request);

	/**
	 * 机构授信信息查询
	 * @param request
	 * @return
	 */
	QueryCreditMerchantInfoResponse queryCreditMerchantInfo(QueryCreditMerchantInfoRequest request);
	
	/**
	 * 个人贷款申请
	 * @param request
	 * @return
	 */
	NewLoanApplyResponse newLoanApply(NewLoanApplyRequest request);
	
	/**
	 * 终止贷款
	 * @param request
	 * @return
	 */
	StopLoanResponse stopLoan(StopLoanRequest request);
	
	/**
	 * 主动还款
	 * @param request
	 * @return
	 */
	UserInitiativeRepamentResponse userInitiativeRepament(UserInitiativeRepamentRequest request);
	
	/**
	 * 查询主动还款
	 * @param request
	 * @return
	 */
	QueryUserInitiativeRepaymentResponse queryUserInitiativeRepayment(QueryUserInitiativeRepaymentRequest request);
	
	/**
	 * 查询贷款订单状态
	 * @param request
	 * @return
	 */
	QueryLoanApplyResponse queryLoanApply(QueryLoanApplyRequest request);
	
	/**
	 * 查询应还款信息
	 * @param request
	 * @return
	 */
	QueryBorrowinfoResponse queryBorrowinfo(QueryBorrowinfoRequest request);
	
	/**
	 * 查询贷款的还款状态及历史
	 * @param request
	 * @return
	 */
	QueryUserRepaymentResponse queryUserRepayment(QueryUserRepaymentRequest request);
	
	
}
