package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class FreezeAccountBalanceResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//是否冻结成功
    private boolean isFreezeSuccess;
    
    //解冻授权码
    private String authcode;
    
	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public boolean isFreezeSuccess() {
		return isFreezeSuccess;
	}

	public void setFreezeSuccess(boolean isFreezeSuccess) {
		this.isFreezeSuccess = isFreezeSuccess;
	}
    
}
