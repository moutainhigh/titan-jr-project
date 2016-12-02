package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;

public class PayMethodConfigResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PayMethodConfigDTO payMethodConfigDTO;

	public PayMethodConfigDTO getPayMethodConfigDTO() {
		return payMethodConfigDTO;
	}

	public void setPayMethodConfigDTO(PayMethodConfigDTO payMethodConfigDTO) {
		this.payMethodConfigDTO = payMethodConfigDTO;
	}
	
}
