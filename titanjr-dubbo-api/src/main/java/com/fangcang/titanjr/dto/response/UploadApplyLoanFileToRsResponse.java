package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

/**
 * 上传贷款文件到融数
 * @author luoqinglong
 * @date   2017年1月3日
 */
public class UploadApplyLoanFileToRsResponse extends BaseResponseDTO {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlKey;

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}
	

}
