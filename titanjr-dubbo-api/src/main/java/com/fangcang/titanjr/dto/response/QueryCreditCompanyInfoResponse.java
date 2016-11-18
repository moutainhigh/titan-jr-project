package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.QueryCreditCompanyInfoDTO;

/**
 * 查询授信申请公司信息
 * @author luoqinglong
 *
 */
public class QueryCreditCompanyInfoResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7097039863838615910L;
	
	private PaginationSupport<QueryCreditCompanyInfoDTO> ;

	public QueryCreditCompanyInfoDTO getQueryCreditCompanyInfoDTO() {
		return queryCreditCompanyInfoDTO;
	}

	public void setQueryCreditCompanyInfoDTO(
			QueryCreditCompanyInfoDTO queryCreditCompanyInfoDTO) {
		this.queryCreditCompanyInfoDTO = queryCreditCompanyInfoDTO;
	}
	
}
