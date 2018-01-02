/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrOrderRefundResponse.java
 * @author Jerry
 * @date 2017年12月18日 下午6:43:43  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联扫码支付订单撤销/退款返回结果
 * @author Jerry
 * @date 2017年12月18日 下午6:43:43  
 */
public class TLQrOrderRefundResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1928585267566724378L;
	private final String isSuccess = "SUCCESS";
	
	public boolean qrCodeResult(){
		if(this.isSuccess.equals(this.retcode)){
			return true;
		}
		return false;
	}
	
	/**
	 * 返回码  SUCCESS/FAIL 
	 * 此字段是通信标识，非交易结果，交易是否成功需要查看trxstatus来判断
	 */
	@NotBlank
	private String retcode;
	/**
	 * 
	 */
	private String retmsg;
	
	
	//以下信息只有当retcode为SUCCESS时有返回
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
	 * 交易单号，收银宝平台的交易流水号
	 */
	@NotBlank
	private String trxid;
	/**
	 * 商户订单号
	 */
	@NotBlank
	private String reqsn;
	/**
	 * 交易状态     0000：交易成功，其他为处理中
	 */
	@NotBlank
	private String trxstatus;
	/**
	 * 交易完成时间
	 */
	private String fintime;
	/**
	 * 错误原因
	 */
	private String errmsg;
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
	
	
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
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
	public String getTrxid() {
		return trxid;
	}
	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}
	public String getReqsn() {
		return reqsn;
	}
	public void setReqsn(String reqsn) {
		this.reqsn = reqsn;
	}
	public String getTrxstatus() {
		return trxstatus;
	}
	public void setTrxstatus(String trxstatus) {
		this.trxstatus = trxstatus;
	}
	public String getFintime() {
		return fintime;
	}
	public void setFintime(String fintime) {
		this.fintime = fintime;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
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
