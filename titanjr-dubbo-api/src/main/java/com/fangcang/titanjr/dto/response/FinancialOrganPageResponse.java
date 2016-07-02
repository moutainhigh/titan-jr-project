package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CheckStatus;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;

import java.util.List;

/**
 * 机构查询返回结果
 * Created by zhaoshan on 2016/3/30.
 */
public class FinancialOrganPageResponse extends BaseResponseDTO{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3367721249067917266L;
	private PaginationSupport<FinancialOrganDTO> pagePaginationSupport;

	public PaginationSupport<FinancialOrganDTO> getPagePaginationSupport() {
		return pagePaginationSupport;
	}

	public void setPagePaginationSupport(
			PaginationSupport<FinancialOrganDTO> pagePaginationSupport) {
		this.pagePaginationSupport = pagePaginationSupport;
	}

  
}
