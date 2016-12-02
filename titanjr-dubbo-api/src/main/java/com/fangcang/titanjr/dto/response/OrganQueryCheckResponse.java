package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganQueryCheckResponse extends BaseResponseDTO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1816271231901666360L;
	
	private PaginationSupport<OrgCheckDTO> paginationSupport;

	public PaginationSupport<OrgCheckDTO> getPaginationSupport() {
		return paginationSupport;
	}

	public void setPaginationSupport(
			PaginationSupport<OrgCheckDTO> paginationSupport) {
		this.paginationSupport = paginationSupport;
	}
	
}
