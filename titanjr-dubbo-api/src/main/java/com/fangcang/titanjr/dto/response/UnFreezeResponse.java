package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;

public class UnFreezeResponse extends BaseResponseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<FundFreezeDTO> fundFreezeDTO;


	public List<FundFreezeDTO> getFundFreezeDTO() {
		return fundFreezeDTO;
	}


	public void setFundFreezeDTO(List<FundFreezeDTO> fundFreezeDTO) {
		this.fundFreezeDTO = fundFreezeDTO;
	}

}
