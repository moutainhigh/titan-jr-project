/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanReSendMsgResponse.java
 * @author Jerry
 * @date 2018年1月5日 下午3:19:04  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年1月5日 下午3:19:04  
 */
public class TitanReSendMsgResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -8903008387332808125L;
	
	/**
	 * 商户订单号
	 */
	@NotBlank
	private String orderNo;
	/**
	 * 支付方式   41：新快捷支付
	 */
	@NotBlank
	private String payType;
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}

}
