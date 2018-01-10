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
 * @author Jerry
 * @date 2018年1月5日 下午3:57:38  
 */
public class RBQuickPayQueryRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 2617838474336500913L;
	
	/**
	 * 商户订单号
	 */
	@NotBlank
	private String order_no;
	

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

}
