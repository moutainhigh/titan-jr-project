/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBCardBINQueryResponse.java
 * @author Jerry
 * @date 2018年1月4日 下午4:45:03  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月4日 下午4:45:03  
 */
public class RBCardBINQueryResponse extends RBBaseResponse {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 2666381507125213138L;
	
	/**
	 * 银行名称
	 */
	private String bank_name;
	/**
	 * 银行编码
	 */
	private String bank_code;
	/**
	 * 卡类型     0.储蓄卡，1.信用卡
	 */
	private String bank_card_type;
	/**
	 * 银行卡号
	 */
	private String card_no;
	
	
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_card_type() {
		return bank_card_type;
	}
	public void setBank_card_type(String bank_card_type) {
		this.bank_card_type = bank_card_type;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
