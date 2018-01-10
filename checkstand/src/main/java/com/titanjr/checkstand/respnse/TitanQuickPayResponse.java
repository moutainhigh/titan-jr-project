/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanQuickPayResponse.java
 * @author Jerry
 * @date 2018年1月3日 上午9:42:37  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 快捷支付返回对象
 * @author Jerry
 * @date 2018年1月3日 上午9:42:37  
 */
public class TitanQuickPayResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 4890408406104417347L;
	
	/**
	 * 订单金额，分
	 */
	@NotBlank
	private String orderAmount;
	/**
	 * 订单提交时间  yyyyMMddHHmmss
	 */
	@NotBlank
	private String orderTime;
	/**
	 * 订单金额
	 */
	@NotBlank
	private String payType;
	/**
	 * 支付信息
	 */
	private String payMsg;
	/**
	 * 卡密校验标识   需要卡密校验返回1，不需要卡密校验不返回此值
	 */
	private String certificate;
	
	
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayMsg() {
		return payMsg;
	}
	public void setPayMsg(String payMsg) {
		this.payMsg = payMsg;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
