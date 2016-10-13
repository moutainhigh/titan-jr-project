package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpTypeDTO;

public class QueryPageHelpTypeResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1563364203838135159L;
	private PaginationSupport<TitanHelpTypeDTO>  page;
	
	public PaginationSupport<TitanHelpTypeDTO> getPage() {
		return page;
	}
	public void setPage(PaginationSupport<TitanHelpTypeDTO> page) {
		this.page = page;
	}
	
	
}
