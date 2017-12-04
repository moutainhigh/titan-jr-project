/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName PayQueryRequest.java
 * @author Jerry
 * @date 2017年11月29日 上午11:41:09  
 */
package com.titanjr.checkstand.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2017年11月29日 上午11:41:09  
 */
public class TLNetBankPayQueryRequest extends BaseRequest {

	/**
	 * 商户号
	 */
	@NotBlank
	private String merchantId;
	
	/**
	 * 网关查询接口版本，固定值：v1.5
	 */
	@NotBlank
	private String version;
	
	/**
	 * 签名类型，与提交订单填写的值保持一致
	 */
	@NotBlank
	private String signType;

	/**
	 * 商户订单号
	 */
	@NotBlank
	private String orderNo;

	/**
	 * 商户订单提交时间，与提交订单时的商户订单提交时间保持一致，仅支持查询(当前时间-31天)以内的订单
	 */
	@NotBlank
	private String orderDatetime;

	/**
	 * 商户提交查询的时间，此时间不能与系统当前时间相差15分钟
	 */
	@NotBlank
	private String queryDatetime;
	
	/**
	 * 签名字符串，以上所有非空参数按上述顺序与密钥key组合，经加密后生成该值
	 */
	@NotBlank
	private String signMsg;
	

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public String getQueryDatetime() {
		return queryDatetime;
	}

	public void setQueryDatetime(String queryDatetime) {
		this.queryDatetime = queryDatetime;
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
