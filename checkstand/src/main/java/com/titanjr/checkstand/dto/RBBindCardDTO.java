/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBBindCardDTO.java
 * @author Jerry
 * @date 2018年1月9日 下午3:43:19  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月9日 下午3:43:19  
 */
public class RBBindCardDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 3568116948473859316L;
	
	/**
	 * 卡类型   0表示储蓄卡，1表示信用卡
	 */
	private String bank_card_type;
	/**
	 * 银行卡的总行名称
	 */
	private String bank_name;
	/**
	 * 银行编码  仅限V3.1.2
	 */
	private String bank_code;
	/**
	 * 绑卡id
	 */
	private String bind_id;
	/**
	 * 银行卡号后4位
	 */
	private String card_last;
	/**
	 * 银行卡号前6位
	 */
	private String card_top;
	/**
	 * 银行预留手机号
	 */
	private String phone;
	

	public String getBank_card_type() {
		return bank_card_type;
	}

	public void setBank_card_type(String bank_card_type) {
		this.bank_card_type = bank_card_type;
	}

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

	public String getBind_id() {
		return bind_id;
	}

	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}

	public String getCard_last() {
		return card_last;
	}

	public void setCard_last(String card_last) {
		this.card_last = card_last;
	}

	public String getCard_top() {
		return card_top;
	}

	public void setCard_top(String card_top) {
		this.card_top = card_top;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
