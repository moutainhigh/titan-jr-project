package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpDTO;

public class QueryPageHelpResponse extends BaseResponseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 499588316693841691L;
	private PaginationSupport<TitanHelpDTO>  page;
	
	public PaginationSupport<TitanHelpDTO> getPage() {
		return page;
	}
	public void setPage(PaginationSupport<TitanHelpDTO> page) {
		this.page = page;
	}
	
}
