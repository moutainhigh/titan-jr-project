package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.PayMethod;

public class TitanPayMethodResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6279301792365298726L;
	private PayMethod payMethod;

	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}
	
}
