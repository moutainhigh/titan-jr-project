/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQuickPayRequest.java
 * @author Jerry
 * @date 2018年1月3日 上午10:42:22  
 */
package com.titanjr.checkstand.request;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 融宝快捷支付请求参数
 * @author Jerry
 * @date 2018年1月3日 上午10:42:22  
 */
public class RBQuickPayRequest extends RBBaseRequest {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -4527585486836605196L;
	
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String card_no;
	/**
	 * 持卡人姓名
	 */
	@NotBlank
	private String owner;
	/**
	 * 证件类型    只支持身份证（01）
	 */
	@NotBlank 
	private String cert_type;
	/**
	 * 证件号
	 */
	@NotBlank 
	private String cert_no;
	/**
	 * 手机号
	 */
	@NotBlank 
	private String phone;
	
	/**
	 * 安全码，信用卡背后的3位数字
	 */
	private String cvv2;
	
	/**
	 * 卡有效期，月年格式  例如2020年09月应写为0920
	 */
	private String validthru;
	
	/**
	 * 商户订单号
	 */
	@NotBlank 
	private String order_no;
	/**
	 * 交易时间   时间戳，精确到秒，2015-03-06 12:24:59
	 */
	@NotBlank 
	private String transtime;
	/**
	 * 交易币种   人名币156
	 */
	@NotBlank 
	private String currency;
	/**
	 * 交易金额   以分为单位的整数，必须大于零
	 */
	@NotNull
	private Integer total_fee;
	/**
	 * 商品名称
	 */
	@NotBlank
	private String title;
	/**
	 * 商品描述
	 */
	@NotBlank
	private String body;
	/**
	 * 商户的用户ID
	 */
	@NotBlank
	private String member_id;
	/**
	 * 终端类型  web、wap、mobile
	 */
	@NotBlank
	private String terminal_type;
	/**
	 * 终端信息    格式为IMEI_MAC/序列号_SIM
	 */
	@NotBlank
	private String terminal_info;
	/**
	 * 用户真实的IP地址
	 */
	@NotBlank
	private String member_ip;
	/**
	 * 签约融宝支付账号或卖家收款融宝支付帐户
	 */
	@NotBlank
	private String seller_email;
	/**
	 * 商户后台系统的回调地址
	 */
	@NotBlank
	private String notify_url;
	/**
	 * 指纹ID，融宝用于交易时风控进行安全的判断，100位以内，建议用UUID的生成方法
	 */
	@NotBlank
	private String token_id;
	/**
	 * 订单关闭时间 ，默认时间是3d，即3天<br>
	 * 取值范围：5m～3d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）<br>
	 * 该参数数值不接受小数点， 如 1.5h，可转换为 90m
	 */
	private String time_expire;
	
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCert_type() {
		return cert_type;
	}
	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}
	public String getCert_no() {
		return cert_no;
	}
	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getValidthru() {
		return validthru;
	}
	public void setValidthru(String validthru) {
		this.validthru = validthru;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getTranstime() {
		return transtime;
	}
	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTerminal_type() {
		return terminal_type;
	}
	public void setTerminal_type(String terminal_type) {
		this.terminal_type = terminal_type;
	}
	public String getTerminal_info() {
		return terminal_info;
	}
	public void setTerminal_info(String terminal_info) {
		this.terminal_info = terminal_info;
	}
	public String getMember_ip() {
		return member_ip;
	}
	public void setMember_ip(String member_ip) {
		this.member_ip = member_ip;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
