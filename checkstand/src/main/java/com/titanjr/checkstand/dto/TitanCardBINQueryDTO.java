/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCardBINQueryDTO.java
 * @author Jerry
 * @date 2018年1月4日 下午5:51:39  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * 卡BIN查询请求对象
 * @author Jerry
 * @date 2018年1月4日 下午5:51:39  
 */
public class TitanCardBINQueryDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1853768672398853019L;
	
	/**
	 * 商户号
	 */
	@NotBlank
	private String merchantNo;
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String cardNo;
	/**
	 * 业务号  {@link BusiCodeEnum}
	 */
	@NotBlank
	private String busiCode;
	/**
	 * 版本号  新版本：v1.1（含快捷支付）
	 */
	@NotBlank
	private String version;
	/**
	 * 签名的类型，默认1为MD5加签
	 */
	@NotBlank
	private String signType;
	/**
	 * 签名字符串
	 */
	@NotBlank
	private String signMsg;
	
	
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

}
