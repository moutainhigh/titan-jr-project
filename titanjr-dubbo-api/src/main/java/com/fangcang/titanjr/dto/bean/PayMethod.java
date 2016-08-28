package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class PayMethod implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6696554744224511782L;
	private String gatewayURL;
    private String checkKey;
    private String titanjrCheckKey;
	public String getGatewayURL() {
		return gatewayURL;
	}
	public void setGatewayURL(String gatewayURL) {
		this.gatewayURL = gatewayURL;
	}
	public String getCheckKey() {
		return checkKey;
	}
	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}
	public String getTitanjrCheckKey() {
		return titanjrCheckKey;
	}
	public void setTitanjrCheckKey(String titanjrCheckKey) {
		this.titanjrCheckKey = titanjrCheckKey;
	}
    
    
}
