/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBDataRequest.java
 * @author Jerry
 * @date 2018年1月3日 下午5:20:45  
 */
package com.titanjr.checkstand.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 融宝api接收的数据结构
 * @author Jerry
 * @date 2018年1月3日 下午5:20:45  
 */
public class RBDataRequest implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -8489193389944102542L;
	
	/**
	 * 商户ID
	 */
	@NotBlank
	private String merchant_id;
	
	/**
	 * 带签名的JSON串加密后的密文
	 */
	@NotBlank
	private String data;
	
	/**
	 * 商户随机生成AESKey,用于AES加密（长度为16位，可以用26个字母和数字组成）
	 */
	@NotBlank
	private String encryptkey;
	
	/**
	 * 版本号  1.0 -- 代付有这个参数，快捷支付没有
	 */
	private String version;
	

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEncryptkey() {
		return encryptkey;
	}

	public void setEncryptkey(String encryptkey) {
		this.encryptkey = encryptkey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
