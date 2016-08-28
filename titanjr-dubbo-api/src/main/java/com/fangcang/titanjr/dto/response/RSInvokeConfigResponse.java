package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.InvokeConfig;


public class RSInvokeConfigResponse extends BaseResponseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6403802635351199449L;
	private InvokeConfig invokeConfig;
	
	public InvokeConfig getInvokeConfig() {
		return invokeConfig;
	}
	public void setInvokeConfig(InvokeConfig invokeConfig) {
		this.invokeConfig = invokeConfig;
	}
	
}
