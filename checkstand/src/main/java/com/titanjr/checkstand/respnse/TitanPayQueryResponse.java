/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLNBPayOrderQueryResponse.java
 * @author Jerry
 * @date 2017年12月1日 上午11:23:27  
 */
package com.titanjr.checkstand.respnse;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询结果
 * @author Jerry
 * @date 2017年12月1日 上午11:23:27  
 */
public class TitanPayQueryResponse extends RSResponse {
	/** 
	 * 
	 */
	private static final long serialVersionUID = -3738191464713097324L;

	/**
	 * 订单金额
	 */
	@NotBlank
	private String orderAmount;

	/**
	 * 订单提交时间
	 */
	@NotBlank
	private String orderTime;
	
	/**
	 * 订单状态     0受理中 ，1未支付  ，2支付中  ， 3支付成功  ， 4支付失败
	 */
	@NotBlank
	private String payStatsu;

	/**
	 * 支付订单号
	 */
	@NotBlank
	private String orderpayNo;

	/**
	 * 支付金额
	 */
	@NotBlank
	private String payAmount;

	/**
	 * 订单支付时间
	 */
	@NotBlank
	private String orderPayTime;

	/**
	 * 支付信息
	 */
	private String payMsg;
	

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

	public String getPayStatsu() {
		return payStatsu;
	}

	public void setPayStatsu(String payStatsu) {
		this.payStatsu = payStatsu;
	}

	public String getOrderpayNo() {
		return orderpayNo;
	}

	public void setOrderpayNo(String orderpayNo) {
		this.orderpayNo = orderpayNo;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(String orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public String getPayMsg() {
		return payMsg;
	}

	public void setPayMsg(String payMsg) {
		this.payMsg = payMsg;
	}

}
