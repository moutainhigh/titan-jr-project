package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;

public class RechargeResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RechargeDataDTO rechargeDataDTO;

	public RechargeDataDTO getRechargeDataDTO() {
		return rechargeDataDTO;
	}

	public void setRechargeDataDTO(RechargeDataDTO rechargeDataDTO) {
		this.rechargeDataDTO = rechargeDataDTO;
	}

}
