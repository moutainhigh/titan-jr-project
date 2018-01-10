/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBReSendMsgResponse.java
 * @author Jerry
 * @date 2018年1月5日 下午3:12:44  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月5日 下午3:12:44  
 */
public class RBReSendMsgResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 3492420727225684928L;
	
	/**
	 * 商户订单号
	 */
	private String order_no;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
