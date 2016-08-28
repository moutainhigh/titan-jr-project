package com.fangcang.titanjr.dto.response;


import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CallBackConfig;

public class TitanCallBackConfigResponse extends BaseResponseDTO {
	private List<CallBackConfig> callBackConfigList;

	public List<CallBackConfig> getCallBackConfigList() {
		return callBackConfigList;
	}

	public void setCallBackConfigList(List<CallBackConfig> callBackConfigList) {
		this.callBackConfigList = callBackConfigList;
	}
	
}
