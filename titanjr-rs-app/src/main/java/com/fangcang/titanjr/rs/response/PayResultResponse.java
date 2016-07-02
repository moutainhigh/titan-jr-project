package com.fangcang.titanjr.rs.response;

import com.fangcang.titanjr.rs.dto.RSPayResultInfo;

public class PayResultResponse extends BaseResponse{
	
	private RSPayResultInfo rsRayResultInfo;

	public RSPayResultInfo getRsRayResultInfo() {
		return rsRayResultInfo;
	}

	public void setRsRayResultInfo(RSPayResultInfo rsRayResultInfo) {
		this.rsRayResultInfo = rsRayResultInfo;
	}
	
}
