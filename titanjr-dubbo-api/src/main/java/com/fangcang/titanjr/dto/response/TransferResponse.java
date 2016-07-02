package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class TransferResponse extends BaseResponseDTO {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//转账的请求号	
	private String requestNo;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
}
