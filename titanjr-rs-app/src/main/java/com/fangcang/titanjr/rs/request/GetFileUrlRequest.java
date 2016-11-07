package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

public class GetFileUrlRequest extends BaseRequest {
	
	private String urlKey;
	
	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
		
	}
	
}
