package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 授信申请单审核
 * @author luoqinglong
 * @date   2016年11月29日
 */
public class AuditCreditOrderRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;
	//考慮到審核人是後台用戶，則這個字段則為用戶ID
	//private String userId;

	// 授信单号
	private String orderNo;

	private LoanCreditStatusEnum loanCreditStatusEnum;

	private String content;

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public LoanCreditStatusEnum getLoanCreditStatusEnum() {
		return loanCreditStatusEnum;
	}

	public void setLoanCreditStatusEnum(LoanCreditStatusEnum loanCreditStatusEnum) {
		this.loanCreditStatusEnum = loanCreditStatusEnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
