package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;

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
		// TODO Auto-generated method stub
		
	}
	
}
