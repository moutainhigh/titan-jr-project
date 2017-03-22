package com.fangcang.titanjr.dto.response;


import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CoopDTO;
/**
 * 合作方信息
 * @author luoqinglong
 * @date   2017年2月15日
 */
public class CoopResponse extends BaseResponseDTO {
	
	CoopDTO coopDTO;

	public CoopDTO getCoopDTO() {
		return coopDTO;
	}

	public void setCoopDTO(CoopDTO coopDTO) {
		this.coopDTO = coopDTO;
	}
	
}
