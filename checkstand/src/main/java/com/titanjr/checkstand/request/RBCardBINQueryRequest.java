/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBCardBINQueryRequest.java
 * @author Jerry
 * @date 2018年1月4日 下午4:42:14  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年1月4日 下午4:42:14  
 */
public class RBCardBINQueryRequest extends RBBaseRequest {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 4551790039120468822L;
	
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String card_no;

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

}
