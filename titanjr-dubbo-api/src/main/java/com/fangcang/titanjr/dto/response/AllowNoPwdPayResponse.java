package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class AllowNoPwdPayResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isAllowNoPwdPay;

	public boolean isAllowNoPwdPay() {
		return isAllowNoPwdPay;
	}

	public void setAllowNoPwdPay(boolean isAllowNoPwdPay) {
		this.isAllowNoPwdPay = isAllowNoPwdPay;
	}
	
}
