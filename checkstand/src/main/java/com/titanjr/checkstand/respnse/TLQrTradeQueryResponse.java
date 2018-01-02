/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrRefundQueryResponse.java
 * @author Jerry
 * @date 2017年12月20日 上午11:02:55  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2017年12月20日 上午11:02:55  
 */
public class TLQrTradeQueryResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -5258284991396924056L;
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
	 * 返回码说明
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
	 * 如支付宝,微信平台的交易单号
	 */
	private String chnltrxid;
	/**
	 * 商户订单号
	 */
	@NotBlank
	private String reqsn;
	/**
	 * 交易类型   VSP501：微信支付     VSP511:支付宝支付
	 */
	@NotBlank
	private String trxcode;
	/**
	 * 交易金额，单位为分
	 */
	@NotBlank
	private String trxamt;
	/**
	 * 交易状态     0000：交易成功
	 */
	@NotBlank
	private String trxstatus;
	/**
	 * 支付平台用户标识，如果为空,则默认填000000<br>
	 * JS支付时使用，微信支付-用户的微信openid，支付宝支付-用户user_id
	 */
	private String acct;
	/**
	 * 交易完成时间  yyyyMMddHHmmss
	 */
	private String fintime;
	/**
	 * 随机字符串
	 */
	private String randomstr;
	/**
	 * 错误原因
	 */
	private String errmsg;
	/**
	 * 如支付宝,微信平台的交易单号
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
	public String getChnltrxid() {
		return chnltrxid;
	}
	public void setChnltrxid(String chnltrxid) {
		this.chnltrxid = chnltrxid;
	}
	public String getReqsn() {
		return reqsn;
	}
	public void setReqsn(String reqsn) {
		this.reqsn = reqsn;
	}
	public String getTrxcode() {
		return trxcode;
	}
	public void setTrxcode(String trxcode) {
		this.trxcode = trxcode;
	}
	public String getTrxamt() {
		return trxamt;
	}
	public void setTrxamt(String trxamt) {
		this.trxamt = trxamt;
	}
	public String getTrxstatus() {
		return trxstatus;
	}
	public void setTrxstatus(String trxstatus) {
		this.trxstatus = trxstatus;
	}
	public String getAcct() {
		return acct;
	}
	public void setAcct(String acct) {
		this.acct = acct;
	}
	public String getFintime() {
		return fintime;
	}
	public void setFintime(String fintime) {
		this.fintime = fintime;
	}
	public String getRandomstr() {
		return randomstr;
	}
	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIsSuccess() {
		return isSuccess;
	}

}
