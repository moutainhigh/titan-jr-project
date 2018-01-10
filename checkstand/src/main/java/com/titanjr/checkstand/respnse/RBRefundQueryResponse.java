/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBPayQueryResponse.java
 * @author Jerry
 * @date 2018年1月5日 下午3:59:42  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月5日 下午3:59:42  
 */
public class RBRefundQueryResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 8872279888481084656L;
	
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 退款金额  单位：分
	 */
	private String total_fee;
	/**
	 * 退款订单状态	processing (处理中)、completed  (退款成功)、failed (退款失败)
	 */
	private String status;
	/**
	 * 签名
	 */
	private String sign;
	
	
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

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
