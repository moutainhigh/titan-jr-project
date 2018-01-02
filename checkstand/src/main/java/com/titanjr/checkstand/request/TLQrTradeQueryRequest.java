/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrRefundQueryRequest.java
 * @author Jerry
 * @date 2017年12月20日 上午10:50:02  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联扫码支付退款查询请求对象
 * @author Jerry
 * @date 2017年12月20日 上午10:50:02  
 */
public class TLQrTradeQueryRequest extends TLBaseRequest {
	
	/**
	 * 商户号
	 */
	@NotBlank
	private String cusid;
	/**
	 * 应用ID
	 */
	@NotBlank
	private String appid;
	/**
	 * 版本号  默认填11
	 */
	@NotBlank
	private String version;
	/**
	 * 商户退款订单号
	 */
	@NotBlank
	private String reqsn;
	/**
	 * 平台交易流水
	 */
	private String trxid;
	/**
	 * 随机字符串
	 */
	@NotBlank
	private String randomstr;
	/**
	 * 签名
	 */
	@NotBlank
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
	public String getReqsn() {
		return reqsn;
	}
	public void setReqsn(String reqsn) {
		this.reqsn = reqsn;
	}
	public String getTrxid() {
		return trxid;
	}
	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}
	public String getRandomstr() {
		return randomstr;
	}
	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
