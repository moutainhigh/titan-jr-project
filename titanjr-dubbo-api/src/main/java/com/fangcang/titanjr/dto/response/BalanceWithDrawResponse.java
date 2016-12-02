package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class BalanceWithDrawResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean operateStatus;

	public boolean isOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(boolean operateStatus) {
		this.operateStatus = operateStatus;
	}
	

}
