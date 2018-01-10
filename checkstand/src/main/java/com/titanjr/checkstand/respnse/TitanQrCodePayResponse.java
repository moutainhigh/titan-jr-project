/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanQrPayResponse.java
 * @author Jerry
 * @date 2017年12月18日 上午11:17:16  
 */
package com.titanjr.checkstand.respnse;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 第三方支付响应
 * @author Jerry
 * @date 2017年12月18日 上午11:17:16  
 */
public class TitanQrCodePayResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -366767211269965182L;
	
	/**
	 * 订单金额，分
	 */
	@NotBlank
	private String orderAmount;
	/**
	 * 订单提交时间   yyyyMMddHHmmss
	 */
	@NotBlank
	private String orderTime;
	/**
	 * 支付方式
	 */
	@NotBlank
	private String payType;
	/**
	 * 支付信息
	 */
	@NotBlank
	private String payMsg;
	/**
	 * 微信支付返回串
	 */
	@NotBlank
	private String respJs;
	
	
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
	public String getRespJs() {
		return respJs;
	}
	public void setRespJs(String respJs) {
		this.respJs = respJs;
	}

}
