package com.fangcang.titanjr.request;

import com.fangcang.titanjr.dto.EscrowedEnum;
import com.fangcang.titanjr.dto.PaySourceEnum;

public class TitanOrderPaymentRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String merchantCode;
	
	private String payOrderNo;
	
	//来源
	private PaySourceEnum paySource;
	
	//房仓的用户Id
	private String fcUserid;
	
	//操作者
	private String operater;
	
	//订单号
	private String businessOrderCode;
	
	//接受账户
	private String recieveMerchantCode;
	
	//是否冻结
	private EscrowedEnum isEscrowed;
	
	//解冻日期
	private String escrowedDate;
	

	public String getRecieveMerchantCode() {
		return recieveMerchantCode;
	}

	public void setRecieveMerchantCode(String recieveMerchantCode) {
		this.recieveMerchantCode = recieveMerchantCode;
	}

	public EscrowedEnum getIsEscrowed() {
		return isEscrowed;
	}

	public void setIsEscrowed(EscrowedEnum isEscrowed) {
		this.isEscrowed = isEscrowed;
	}

	public String getEscrowedDate() {
		return escrowedDate;
	}

	public void setEscrowedDate(String escrowedDate) {
		this.escrowedDate = escrowedDate;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public PaySourceEnum getPaySource() {
		return paySource;
	}

	public void setPaySource(PaySourceEnum paySource) {
		this.paySource = paySource;
	}

	public String getFcUserid() {
		return fcUserid;
	}

	public void setFcUserid(String fcUserid) {
		this.fcUserid = fcUserid;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getBusinessOrderCode() {
		return businessOrderCode;
	}

	public void setBusinessOrderCode(String businessOrderCode) {
		this.businessOrderCode = businessOrderCode;
	}
	
}
