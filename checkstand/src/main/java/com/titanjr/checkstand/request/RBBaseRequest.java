package com.titanjr.checkstand.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 
 * @author Jerry
 * @date 2017年12月20日 上午10:47:19
 */
public class RBBaseRequest implements Serializable {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -7972400392019670577L;

	/**
	 * 商户ID
	 */
	@NotBlank
    private String merchant_id;
	
	/**
	 * 版本号  版本控制默认3.1.3
	 */
	@NotBlank
    private String version;
	
	/**
	 * 签名类型   传MD5
	 */
	@NotBlank
    private String sign_type;
	
	/**
	 * 签名 
	 */
	@NotBlank
    private String sign;
    
	/**
	 * 请求类型，内部使用，非渠道需要字段  @see RequestTypeEnum
	 */
	@NotBlank
    private String requestType;
	

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
