package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class CheckPermissionResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isPermission;

	public boolean isPermission() {
		return isPermission;
	}

	public void setPermission(boolean isPermission) {
		this.isPermission = isPermission;
	}
	

}
