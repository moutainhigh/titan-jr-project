package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class CreateVirtualOrgResponse extends BaseResponseDTO{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String vOrgCode;

	public String getvOrgCode() {
		return vOrgCode;
	}

	public void setvOrgCode(String vOrgCode) {
		this.vOrgCode = vOrgCode;
	}

}
