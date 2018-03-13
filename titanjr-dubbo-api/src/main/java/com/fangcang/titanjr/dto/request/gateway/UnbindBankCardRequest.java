package com.fangcang.titanjr.dto.request.gateway;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UnbindBankCardRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3012117947863462625L;
	
	//解绑卡接口：112
	private String busiCode;
	
	private String merchantNo;
	
	//本地绑卡ID
	private String commonPayId;
	
	//融宝绑卡ID
	private String bindCardId;
	
	//用户ID（身份证号还可以当作融宝的用户ID）
	private String userId;
	
	//身份证号
	private String idCode;
	
	private String signType;
	
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

	public String getCommonPayId() {
		return commonPayId;
	}

	public void setCommonPayId(String commonPayId) {
		this.commonPayId = commonPayId;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
