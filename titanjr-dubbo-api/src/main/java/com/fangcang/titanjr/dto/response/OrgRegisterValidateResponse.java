package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
/**
 * 机构注册前校验 是否已经注册
 * @author luoqinglong
 * @2016年5月17日
 */
public class OrgRegisterValidateResponse extends BaseResponseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6582033421894307123L;
	//已经注册的机构
	OrgDTO orgDTO;

	public OrgDTO getOrgDTO() {
		return orgDTO;
	}

	public void setOrgDTO(OrgDTO orgDTO) {
		this.orgDTO = orgDTO;
	}
	
	
}
