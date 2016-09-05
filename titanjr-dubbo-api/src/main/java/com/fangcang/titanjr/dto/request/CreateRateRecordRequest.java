package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.TitanRateRecordDto;

public class CreateRateRecordRequest extends BaseRequestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TitanRateRecordDto titanRateRecordDto;

	public TitanRateRecordDto getTitanRateRecordDto() {
		return titanRateRecordDto;
	}

	public void setTitanRateRecordDto(TitanRateRecordDto titanRateRecordDto) {
		this.titanRateRecordDto = titanRateRecordDto;
	}
}
