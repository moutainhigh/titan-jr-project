/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBReSendMsgRequest.java
 * @author Jerry
 * @date 2018年1月5日 下午3:10:37  
 */
package com.titanjr.checkstand.request;

/**
 * @author Jerry
 * @date 2018年1月5日 下午3:10:37  
 */
public class RBReSendMsgRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -5407577915112476691L;
	
	/**
	 * 商户订单号
	 */
	private String order_no;
	
	/**
	 * 手机号
	 */
	private String phone;
	

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
