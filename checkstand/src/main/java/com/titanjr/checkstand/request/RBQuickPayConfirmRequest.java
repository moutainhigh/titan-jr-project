/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBPayConfirmRequest.java
 * @author Jerry
 * @date 2018年1月5日 上午11:12:46  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年1月5日 上午11:12:46  
 */
public class RBQuickPayConfirmRequest extends RBBaseRequest {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 5431134663504865796L;
	/**
	 * 商户订单号
	 */
	@NotBlank
	private String order_no;
	/**
	 * 短信验证码
	 */
	@NotBlank
	private String check_code;
	
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}

}
