package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.bean.TransOrderDTO;

public class ConfirmFinanceRequest {
	
	private TransOrderDTO transOrderDTO;
	
	private int status = 1;//1 成功 2 申请中  3 付款失败

	public TransOrderDTO getTransOrderDTO() {
		return transOrderDTO;
	}

	public void setTransOrderDTO(TransOrderDTO transOrderDTO) {
		this.transOrderDTO = transOrderDTO;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
