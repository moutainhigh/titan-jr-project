package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.bean.TransOrderDTO;

import java.io.Serializable;

public class ConfirmFinanceRequest implements Serializable{
	
	private TransOrderDTO transOrderDTO;
	
	private int status = 1;//1 成功 2 申请中  3 付款失败
	/**
	 * 是否需要记录异常日志,默认要记
	 */
	private boolean isSaveLog = true;
	
	
	

	public boolean getIsSaveLog() {
		return isSaveLog;
	}

	public void setIsSaveLog(boolean isSaveLog) {
		this.isSaveLog = isSaveLog;
	}

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
