package com.fangcang.titanjr.dto.request.gateway;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 查询快捷支付绑卡信息请求参数
 * @author jerry
 * @date 2017.6.16
 */
public class QueryQuickPayBindCardRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2505975717536517650L;

	// 查询绑卡接口：111
	private String busiCode;
	
	private String merchantNo;
	
	//身份证号（身份证号还可以当作融宝的用户ID）
	private String idCode;
	
	//卡类型  10储蓄卡  11信用卡
	private String cardType;

	//签名的类型，默认1为MD5加签
	private String signType;
	
	//新版本：v1.1（含快捷支付）
	private String version;
	
	private String signMsg;

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
