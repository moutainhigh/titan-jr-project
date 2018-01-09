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
public class RBPayQueryResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 8872279888481084656L;
	
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 融宝交易流水号
	 */
	private String trade_no;
	/**
	 * 金额  单位：分
	 */
	private String total_fee;
	/**
	 * 订单状态	completed交易完成，failed支付失败，processing交易处理中，wait等待买家付款，closed订单关闭
	 */
	private String status;
	/**
	 * 时间戳   格式：2018-01-05 17:39:58
	 */
	private String timestamp;
	
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
