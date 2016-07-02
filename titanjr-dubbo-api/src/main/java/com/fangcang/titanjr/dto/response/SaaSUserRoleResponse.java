package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.SaaSMerchantUserDTO;

public class SaaSUserRoleResponse extends BaseResponseDTO{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5351913322059372946L;
	PaginationSupport<SaaSMerchantUserDTO> paginationSupport;

	public PaginationSupport<SaaSMerchantUserDTO> getPaginationSupport() {
		return paginationSupport;
	}

	public void setPaginationSupport(
			PaginationSupport<SaaSMerchantUserDTO> paginationSupport) {
		this.paginationSupport = paginationSupport;
	}
	
	
}
