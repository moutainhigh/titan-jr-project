/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QrCodeOrderRefundRequest.java
 * @author Jerry
 * @date 2017年12月18日 下午6:33:35  
 */
package com.titanjr.checkstand.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 第三方扫码订单撤销及退款请求对象
 * @author Jerry
 * @date 2017年12月18日 下午6:33:35  
 */
public class TLQrOrderRefundRequest extends TLBaseRequest {
	
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
	 * 版本号
	 */
	@NotBlank
	private String version;
	/**
	 * 商户退款交易单号
	 */
	@NotBlank
	private String reqsn;
	/**
	 * 交易金额
	 */
	@NotBlank
	private String trxamt;
	/**
	 * 原交易单号  oldreqsn和oldtrxid必填其一，这里默认oldreqsn必填
	 */
	@NotBlank
	private String oldreqsn;
	/**
	 * 原交易流水
	 */
	private String oldtrxid;
	/**
	 * 备注（退款）
	 */
	private String remark;
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
	public String getTrxamt() {
		return trxamt;
	}
	public void setTrxamt(String trxamt) {
		this.trxamt = trxamt;
	}
	public String getOldreqsn() {
		return oldreqsn;
	}
	public void setOldreqsn(String oldreqsn) {
		this.oldreqsn = oldreqsn;
	}
	public String getOldtrxid() {
		return oldtrxid;
	}
	public void setOldtrxid(String oldtrxid) {
		this.oldtrxid = oldtrxid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
