package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.request.RefundConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;

public interface TitanFinancialRefundService {
	
	/**
	 * 用于商家退款
	 * @param refundTransferRequest
	 * @return
	 */
	public TitanJrRefundResponse refund(TitanJrRefundRequest refundRequest);
	
	/**
	 * 用于定时查询商家退款
	 */
	public void refundConfirm(RefundConfirmRequest refundConfirm);
	
	/**
	 * 查询退款
	 * @param refundDTO
	 * @return
	 */
	public RefundDTO queryRefundRequest(RefundDTO refundDTO);
	
}
