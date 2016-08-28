package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 金融系统处理成功通知第三方的地址
 * @author luoqinglong
 *
 */
public class CallBackConfig implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2244249537706288881L;
	private String paySource;
    private String callBackURL;
    
	public String getPaySource() {
		return paySource;
	}
	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}
	public String getCallBackURL() {
		return callBackURL;
	}
	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}
    
}
