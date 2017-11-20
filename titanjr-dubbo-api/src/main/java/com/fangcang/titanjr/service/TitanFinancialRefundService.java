package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RefundConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
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
	 * 退款通知第三方平台
	 * @param orderId 交易流水号
	 * @param refundStatus 退款状态
	 */
	public void notifyRefund(String orderId,String refundStatus);
	
	/**
	 * 查询退款
	 * @param refundDTO
	 * @return
	 */
	public RefundDTO queryRefundRequest(RefundDTO refundDTO);
	
	/**
	 * 通用退款接口
	 * @author Jerry
	 * @date 2017年8月16日 下午8:45:51
	 */
	public NotifyRefundResponse notifyRefund(RefundOrderRequest refundOrderRequest, NotifyRefundRequest 
			notifyRefundRequest, TransOrderDTO transOrderDTO);
	
}
