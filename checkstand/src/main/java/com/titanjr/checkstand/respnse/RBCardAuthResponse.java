/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBRBCardAuthResponse.java
 * @author Jerry
 * @date 2018年1月8日 下午3:38:50  
 */
package com.titanjr.checkstand.respnse;

/**
 * 融宝卡密鉴权返回结果
 * @author Jerry
 * @date 2018年1月8日 下午3:38:50  
 */
public class RBCardAuthResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1593589758637869651L;
	
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 融宝交易流水号
	 */
	private String trade_no;
	/**
	 * 签约时返回的绑卡ID
	 */
	private String bind_id;
	/**
	 * 只有0储蓄卡
	 */
	private String bank_card_type;
	/**
	 * 只会返回招商银行
	 */
	private String bank_name;
	/**
	 * 银行编码  仅限V3.1.2以上版本
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
	 * 金额  以分为单位
	 */
	private String total_fee;
	/**
	 * 用户ID
	 */
	private String member_id;
	/**
	 * 通知Id 融宝通知表主键
	 */
	private String notify_id;
	
	
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
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

}
