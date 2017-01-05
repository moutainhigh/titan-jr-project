package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;

public class LocalAddTransOrderRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TransOrderDTO transOrderDTO;

	public TransOrderDTO getTransOrderDTO() {
		return transOrderDTO;
	}

	public void setTransOrderDTO(TransOrderDTO transOrderDTO) {
		this.transOrderDTO = transOrderDTO;
	}
	
}
