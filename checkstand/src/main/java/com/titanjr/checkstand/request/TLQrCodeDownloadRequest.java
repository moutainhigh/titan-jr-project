/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrCodeDownloadRequest.java
 * @author Jerry
 * @date 2018年3月16日 上午10:58:05  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年3月16日 上午10:58:05  
 */
public class TLQrCodeDownloadRequest extends TLBaseRequest {
	
	/**
	 * 商户号，平台分配的商户号
	 */
	@NotBlank
	private String cusid;
	
	/**
	 * 应用ID，平台分配的APPID
	 */
	@NotBlank
	private String appid;
	
	/**
	 * 交易日期，yyyyMMdd
	 */
	@NotBlank
	private String date;
	
	/**
	 * 随机字符串
	 */
	@NotBlank
	private String randomstr;

	/**
	 * 签名
	 */
	@NotBlank
	private String sign;
	

	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRandomstr() {
		return randomstr;
	}

	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
