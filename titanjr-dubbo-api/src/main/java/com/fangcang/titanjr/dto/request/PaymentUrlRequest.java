package com.fangcang.titanjr.dto.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class PaymentUrlRequest extends BaseRequestDTO {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String merchantcode;
	
	@NotNull
	private String payOrderNo;
	
	private String userid;
	
	private String fcUserid;
	
	//来源
	private String paySource;
	
	//接收签名
	private String sign;
	
	//操作者
	private String operater;
	
	//接受账户
	private String recieveMerchantCode;
		
	//是否冻结
	private String isEscrowed;
		
	//解冻日期
	private String escrowedDate;

	
	public String getFcUserid() {
		return fcUserid;
	}

	public void setFcUserid(String fcUserid) {
		this.fcUserid = fcUserid;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPaySource() {
		return paySource;
	}

	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getRecieveMerchantCode() {
		return recieveMerchantCode;
	}

	public void setRecieveMerchantCode(String recieveMerchantCode) {
		this.recieveMerchantCode = recieveMerchantCode;
	}

	public String getIsEscrowed() {
		return isEscrowed;
	}

	public void setIsEscrowed(String isEscrowed) {
		this.isEscrowed = isEscrowed;
	}

	public String getEscrowedDate() {
		return escrowedDate;
	}

	public void setEscrowedDate(String escrowedDate) {
		this.escrowedDate = escrowedDate;
	}
}
