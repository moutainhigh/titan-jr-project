package com.fangcang.titanjr.pay.req;

import java.util.Map;

public class TitanNotifyPayResultReq {
	private String url;

	private int resultCode;

	private String resultMsg;

	private Map<String, String> bussInfos = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Map<String, String> getBussInfos() {
		return bussInfos;
	}

	public void setBussInfos(Map<String, String> bussInfos) {
		this.bussInfos = bussInfos;
	}
}
