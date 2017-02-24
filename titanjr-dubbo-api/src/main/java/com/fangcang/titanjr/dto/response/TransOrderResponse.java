package com.fangcang.titanjr.dto.response;

import java.util.Date;
import java.util.List;

import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.BaseResponseDTO;

public class TransOrderResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TransOrderDTO TransOrder;

	public TransOrderDTO getTransOrder() {
		return TransOrder;
	}

	public void setTransOrder(TransOrderDTO transOrder) {
		TransOrder = transOrder;
	}

}
