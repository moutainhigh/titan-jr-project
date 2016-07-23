package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

/**
 * 校验验证码
 * @author luoqinglong
 * @2016年7月21日
 */
public class VerifyCheckCodeResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2511244721392152123L;
	private Integer codeId;
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	
}
