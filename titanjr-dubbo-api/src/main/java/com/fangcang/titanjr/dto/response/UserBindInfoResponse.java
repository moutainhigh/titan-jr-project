package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.UserBindInfoDTO;

public class UserBindInfoResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1712961667766459843L;
	PaginationSupport<UserBindInfoDTO> paginationSupport;

	public PaginationSupport<UserBindInfoDTO> getPaginationSupport() {
		return paginationSupport;
	}

	public void setPaginationSupport(
			PaginationSupport<UserBindInfoDTO> paginationSupport) {
		this.paginationSupport = paginationSupport;
	}
	
	
}
