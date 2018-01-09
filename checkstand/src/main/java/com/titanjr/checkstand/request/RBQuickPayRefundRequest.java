/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBQuickPayRefundRequest.java
 * @author Jerry
 * @date 2018年1月5日 下午5:48:51  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 快捷支付退款请求对象
 * @author Jerry
 * @date 2018年1月5日 下午5:48:51  
 */
public class RBQuickPayRefundRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 8081959261705737012L;
	
	/**
	 * 原商户订单号
	 */
	@NotBlank
 	private String orig_order_no;
	/**
	 * 退款订单号
	 */
	@NotBlank
 	private String order_no;
	/**
	 * 退款金额
	 */
	@NotBlank
 	private String amount;
	/**
	 * 退款说明
	 */
 	private String note;
	/**
	 * 退款通知url（获取退款结果目前采用主动查询策略，可以不传这个url）
	 */
 	private String notify_url;
 	
 	
	public String getOrig_order_no() {
		return orig_order_no;
	}
	public void setOrig_order_no(String orig_order_no) {
		this.orig_order_no = orig_order_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

}
