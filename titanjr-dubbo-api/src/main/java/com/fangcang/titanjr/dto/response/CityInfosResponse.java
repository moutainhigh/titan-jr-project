package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;

public class CityInfosResponse extends  BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CityInfoDTO> cityInfoDTOList;

	public List<CityInfoDTO> getCityInfoDTOList() {
		return cityInfoDTOList;
	}

	public void setCityInfoDTOList(List<CityInfoDTO> cityInfoDTOList) {
		this.cityInfoDTOList = cityInfoDTOList;
	}
	
}
