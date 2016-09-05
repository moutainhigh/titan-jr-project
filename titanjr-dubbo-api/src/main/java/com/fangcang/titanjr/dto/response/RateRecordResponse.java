package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanRateRecordDto;

public class RateRecordResponse extends BaseResponseDTO {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TitanRateRecordDto> titanRateRecords;

	public List<TitanRateRecordDto> getTitanRateRecords() {
		return titanRateRecords;
	}

	public void setTitanRateRecords(List<TitanRateRecordDto> titanRateRecords) {
		this.titanRateRecords = titanRateRecords;
	}
	

}
