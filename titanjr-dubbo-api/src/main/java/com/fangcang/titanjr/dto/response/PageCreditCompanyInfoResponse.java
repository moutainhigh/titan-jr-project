package com.fangcang.titanjr.dto.response;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;

/**
 * 查询授信申请公司信息
 * --报错，改了下--zhaohsan1119；
 * @author luoqinglong
 *
 */
public class PageCreditCompanyInfoResponse extends BaseResponseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7097039863838615910L;
	
	private PaginationSupport<CreditCompanyInfoDTO> pageCreditCompanyInfoDTO;

	public PaginationSupport<CreditCompanyInfoDTO> getPageCreditCompanyInfoDTO() {
		return pageCreditCompanyInfoDTO;
	}

	public void setPageCreditCompanyInfoDTO(
			PaginationSupport<CreditCompanyInfoDTO> pageCreditCompanyInfoDTO) {
		this.pageCreditCompanyInfoDTO = pageCreditCompanyInfoDTO;
	}
	
}
