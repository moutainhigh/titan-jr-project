/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrPayRequest.java
 * @author Jerry
 * @date 2017年12月7日 下午6:17:28  
 */
package com.titanjr.checkstand.request;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联第三方支付请求对象
 * @author Jerry
 * @date 2017年12月7日 下午6:17:28  
 */
public class TLQrCodePayRequest extends TLBaseRequest {
	
	/**
	 * 平台分配的商户号
	 */
	@NotBlank
	private String cusid;

	/**
	 * 平台分配的应用ID
	 */
	@NotBlank
	private String appid;

	/**
	 * 默认填11
	 */
	@NotBlank
	private String version;

	/**
	 * 交易金额，单位为分
	 */
	@NotNull
	private Integer trxamt;

	/**
	 * 商户的交易订单号
	 */
	@NotBlank
	private String reqsn;

	/**
	 * 交易方式 W01：微信扫码支付    A01：支付宝扫码支付
	 */
	@NotBlank
	private String paytype;

	/**
	 * 随机字符串
	 */
	@NotBlank
	private String randomstr;

	/**
	 * 订单商品名称，为空则以商户名作为商品名称
	 * 最大100个字节(50个中文字符)
	 */
	private String body;

	/**
	 * 备注信息  最大50个字节(25个中文字符)
	 */
	private String remark;
	
	/**
	 * 订单有效时间，以分为单位，不填默认为15分钟
	 */
	private String validtime;
	
	/**
	 * 微信支付-用户的微信openid，支付宝支付-用户user_id<br>
	 * 微信公众号及支付宝服务窗不可为空
	 */
	private String acct;
	
	/**
	 * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
	 */
	@NotBlank
	private String notify_url;
	
	/**
	 * 仅支持no_credit--指定不能使用信用卡支付
	 */
	private String limit_pay;
	
	/**
	 * 签名
	 */
	private String sign;
	

	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getTrxamt() {
		return trxamt;
	}

	public void setTrxamt(Integer trxamt) {
		this.trxamt = trxamt;
	}

	public String getReqsn() {
		return reqsn;
	}

	public void setReqsn(String reqsn) {
		this.reqsn = reqsn;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getRandomstr() {
		return randomstr;
	}

	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
