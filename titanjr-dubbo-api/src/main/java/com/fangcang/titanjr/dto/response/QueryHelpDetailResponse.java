package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpDTO;

/**
 * 帮助内容
 * @author luoqinglong
 * @2016年9月22日
 */
public class QueryHelpDetailResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1573398271244957719L;
	
	private TitanHelpDTO titanHelpDTO;

	public TitanHelpDTO getTitanHelpDTO() {
		return titanHelpDTO;
	}

	public void setTitanHelpDTO(TitanHelpDTO titanHelpDTO) {
		this.titanHelpDTO = titanHelpDTO;
	}
	
}
