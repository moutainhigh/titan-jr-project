package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.GDPOrderDTO;

public class GDPOrderResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GDPOrderDTO gDPOrderDTO;

	public GDPOrderDTO getgDPOrderDTO() {
		return gDPOrderDTO;
	}

	public void setgDPOrderDTO(GDPOrderDTO gDPOrderDTO) {
		this.gDPOrderDTO = gDPOrderDTO;
	}
	
	
}
