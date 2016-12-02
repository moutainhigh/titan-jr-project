package com.fangcang.titanjr.dto.response;

import java.io.Serializable;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class PayPasswordResponse extends BaseResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean saveSuccess;

	public boolean isSaveSuccess() {
		return saveSuccess;
	}

	public void setSaveSuccess(boolean saveSuccess) {
		this.saveSuccess = saveSuccess;
	}
	
}
