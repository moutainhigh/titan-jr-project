/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBPayQueryRequest.java
 * @author Jerry
 * @date 2018年1月5日 下午3:57:38  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 快捷支付退款查询请求
 * @author Jerry
 * @date 2018年1月8日 下午2:28:29
 */
public class RBQuickPayRefundQueryRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 2617838474336500913L;
	
	/**
	 * 商户退款订单号
	 */
	@NotBlank
	private String order_no;
	
	/**
	 * 商户原订单号
	 */
	@NotBlank
	private String orig_order_no;
	

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOrig_order_no() {
		return orig_order_no;
	}

	public void setOrig_order_no(String orig_order_no) {
		this.orig_order_no = orig_order_no;
	}

}
