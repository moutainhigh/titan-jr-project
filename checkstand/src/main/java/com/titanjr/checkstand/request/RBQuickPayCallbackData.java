/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBQuickPayCallbackData.java
 * @author Jerry
 * @date 2018年1月5日 下午5:13:42  
 */
package com.titanjr.checkstand.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月5日 下午5:13:42  
 */
public class RBQuickPayCallbackData implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -7884096428663362614L;
	
	/**
	 * 商户ID
	 */
	private String merchant_id;
	/**
	 * 融宝交易流水号
	 */
	private String trade_no;
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 支付金额，以分为单位
	 */
	private String total_fee;
	/**
	 * 状态	TRADE_FAILURE支付失败 ，TRADE_FINISHED支付成功
	 */
	private String status;
	/**
	 * 版本控制默认3.1.3
	 */
	private String version;
	/**
	 * 通知编号
	 */
	private String notify_id;
	/**
	 * 签名类型MD5
	 */
	private String sign_type;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 
	 */
	private String result_code;
	private String result_msg;
	
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_msg() {
		return result_msg;
	}
	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
