package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.InterestRepaymentQueryborrowinfoRequest;
import com.fangcang.titanjr.rs.request.OprsystemCreditCompanyRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditapplicationRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditmerchantinfoqueryRequest;
import com.fangcang.titanjr.rs.request.OrderServiceAgreementconfirmRequest;
import com.fangcang.titanjr.rs.request.OrderServiceNewloanapplyRequest;
import com.fangcang.titanjr.rs.response.InterestRepaymentQueryborrowinfoResponse;
import com.fangcang.titanjr.rs.response.OprsystemCreditCompanyResponse;
import com.fangcang.titanjr.rs.response.OrderMixserviceCreditapplicationResponse;
import com.fangcang.titanjr.rs.response.OrderMixserviceCreditmerchantinfoqueryResponse;
import com.fangcang.titanjr.rs.response.OrderServiceAgreementconfirmResponse;
import com.fangcang.titanjr.rs.response.OrderServiceNewloanapplyResponse;

/**
 * 信贷业务
 * @author luoqinglong
 * @2016年10月31日
 */
public interface RSCreditManager {
	
	/**
	 * 上报企业资料信息
	 */
	OprsystemCreditCompanyResponse oprsystemCreditCompany(OprsystemCreditCompanyRequest request);
	
	/***
	 * 申请授信
	 */
	OrderMixserviceCreditapplicationResponse orderMixserviceCreditapplication(OrderMixserviceCreditapplicationRequest request);
	
	/**
	 * 协议确认
	 * @param request
	 * @return
	 */
	OrderServiceAgreementconfirmResponse orderServiceAgreementConfirm(OrderServiceAgreementconfirmRequest request);

	/**
	 * 机构授信信息查询
	 * @param request
	 * @return
	 */
	OrderMixserviceCreditmerchantinfoqueryResponse orderMixserviceCreditmerchantinfoquery(OrderMixserviceCreditmerchantinfoqueryRequest request);
	
	/**
	 * 个人贷款申请
	 * @param request
	 * @return
	 */
	OrderServiceNewloanapplyResponse orderServiceNewloanapply(OrderServiceNewloanapplyRequest request);
	
	/**
	 * 查询应还款信息
	 * @param request
	 * @return
	 */
	InterestRepaymentQueryborrowinfoResponse interestRepaymentQueryBorrowinfo(InterestRepaymentQueryborrowinfoRequest request);
}
