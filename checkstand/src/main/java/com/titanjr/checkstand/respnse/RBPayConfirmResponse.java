/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBPayConfirmResponse.java
 * @author Jerry
 * @date 2018年1月5日 上午11:17:05  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月5日 上午11:17:05  
 */
public class RBPayConfirmResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 264671459772065067L;
	
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 融宝交易流水号
	 */
	private String trade_no;
	/**
	 * 签约时返回的绑卡ID，V3.1.2以上版本
	 */
	private String bind_id;
	/**
	 * 银行卡类型，0表示储蓄卡，1表示信用卡
	 */
	private String bank_card_type;
	/**
	 * 银行卡名称
	 */
	private String bank_name;
	/**
	 * 银行编码
	 */
	private String bank_code;
	/**
	 * 银行卡号后4位
	 */
	private String card_last;
	/**
	 * 银行预留手机号
	 */
	private String phone;
	/**
	 * 数据返回时间   格式：2018-01-05 11:20:00   （版本号4.0以上有）
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
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
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
	public String getCard_last() {
		return card_last;
	}
	public void setCard_last(String card_last) {
		this.card_last = card_last;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
