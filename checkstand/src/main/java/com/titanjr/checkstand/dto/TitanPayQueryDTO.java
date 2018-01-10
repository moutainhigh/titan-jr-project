package com.titanjr.checkstand.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * 支付查询请求参数（融数格式）
 * @author Jerry
 * @date 2017年12月7日 下午2:39:45
 */
public class TitanPayQueryDTO {
	
	/**
	 * 业务号  {@link BusiCodeEnum}
	 */
	@NotBlank
	private String busiCode;
	@NotBlank
	private String signType;
	@NotBlank
	private String version;
	@NotBlank
	private String merchantNo;
	@NotBlank
	private String orderNo;
	@NotBlank
	private String orderTime;
	private String appid;
	private String trxid;
	private String randomstr;
	@NotBlank
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTrxid() {
		return trxid;
	}
	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}
	public String getRandomstr() {
		return randomstr;
	}
	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
