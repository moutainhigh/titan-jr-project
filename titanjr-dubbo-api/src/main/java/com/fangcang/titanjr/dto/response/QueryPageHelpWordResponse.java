package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.HelpWordDTO;

/**
 * 关键词搜索
 * @author luoqinglong
 * @2016年10月12日
 */
public class QueryPageHelpWordResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5253362983427781672L;
	private PaginationSupport<HelpWordDTO>  page;
	
	public PaginationSupport<HelpWordDTO> getPage() {
		return page;
	}
	public void setPage(PaginationSupport<HelpWordDTO> page) {
		this.page = page;
	}
	
	
}
