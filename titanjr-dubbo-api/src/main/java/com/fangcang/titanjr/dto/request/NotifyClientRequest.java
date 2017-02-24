package com.fangcang.titanjr.dto.request;

import java.util.List;

import org.apache.http.NameValuePair;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class NotifyClientRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//回调的参数
	private List<NameValuePair> params;
	
	
	private String url;


	public List<NameValuePair> getParams() {
		return params;
	}


	public void setParams(List<NameValuePair> params) {
		this.params = params;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
}
