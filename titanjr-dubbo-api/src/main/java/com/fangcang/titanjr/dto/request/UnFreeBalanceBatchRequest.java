package com.fangcang.titanjr.dto.request;

import java.io.Serializable;
import java.util.List;

import com.fangcang.titanjr.dto.bean.FundFreezeDTO;

public class UnFreeBalanceBatchRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FundFreezeDTO> fundFreezeDTOList;

	public List<FundFreezeDTO> getFundFreezeDTOList() {
		return fundFreezeDTOList;
	}

	public void setFundFreezeDTOList(List<FundFreezeDTO> fundFreezeDTOList) {
		this.fundFreezeDTOList = fundFreezeDTOList;
	}
	
	

}
