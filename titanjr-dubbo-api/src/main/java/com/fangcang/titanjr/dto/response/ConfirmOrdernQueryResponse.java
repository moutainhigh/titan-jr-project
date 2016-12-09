package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TransOrderInfo;

public class ConfirmOrdernQueryResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TransOrderInfo> transOrderInfos;

	public List<TransOrderInfo> getTransOrderInfos() {
		return transOrderInfos;
	}

	public void setTransOrderInfos(List<TransOrderInfo> transOrderInfos) {
		this.transOrderInfos = transOrderInfos;
	}
	
}
