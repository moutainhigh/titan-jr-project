/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanUnBindCardDTO.java
 * @author Jerry
 * @date 2018年1月9日 上午11:18:47  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * @author Jerry
 * @date 2018年1月9日 上午11:18:47  
 */
public class TitanUnBindCardDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2586137248384602494L;
	
	/**
	 * 业务号  {@link BusiCodeEnum}
	 */
	@NotBlank
	private String busiCode;
	/**
	 * 商户号
	 */
	@NotBlank
	private String merchantNo;
	/**
	 * 融宝绑卡ID
	 */
	private String bindCardId;
	/**
	 * 用户id
	 */
	@NotBlank
	private String userId;
	/**
	 * 身份证号
	 */
	@NotBlank
	private String idCode;
	/**
	 * 签名的类型，默认1为MD5加签
	 */
	@NotBlank
	private String signType;
	/**
	 * 版本号  新版本：v1.1（含快捷支付）
	 */
	@NotBlank
	private String version;
	/**
	 * 签名字符串
	 */
	@NotBlank
	private String signMsg;
	
	
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getBindCardId() {
		return bindCardId;
	}
	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

}
