/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrCodeDownloadResponse.java
 * @author Jerry
 * @date 2018年3月16日 上午11:10:34  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年3月16日 上午11:10:34  
 */
public class TLQrCodeDownloadResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 8434948184372437664L;
	
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
	 * 下载对账文件的url
	 */
	@NotBlank
	private String url;
	
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
	
	/**
	 * 返回码
	 */
	@NotBlank
	private String retcode;

	/**
	 * 返回码说明
	 */
	private String retmsg;
	

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

}
