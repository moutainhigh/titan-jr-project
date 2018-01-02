/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrPayResponse.java
 * @author Jerry
 * @date 2017年12月7日 下午6:33:15  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联第三方扫码支付返回对象
 * @author Jerry
 * @date 2017年12月7日 下午6:33:15  
 */
public class TLQrCodePayResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -7707317035725347614L;
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
	 * 渠道平台交易单号，例如微信,支付宝平台的交易单号
	 */
	private String chnltrxid;
	/**
	 * 商户的交易订单号
	 */
	@NotBlank
	private String reqsn;
	/**
	 * 随机字符串
	 */
	@NotBlank
	private String randomstr;
	/**
	 * 交易状态
	 * 0000：交易成功
	 * 3045：交易超时
	 * 3008：余额不足
	 * 3999：交易失败
	 * 2008：交易处理中
	 * 3050：交易已撤销
	 */
	@NotBlank
	private String trxstatus;
	/**
	 * 交易完成时间  yyyyMMddHHmmss
	 */
	private String fintime;
	/**
	 * 失败的原因说明
	 */
	private String errmsg;
	/**
	 * 扫码支付则返回二维码字符串
	 */
	private String payinfo;
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
	public String getRandomstr() {
		return randomstr;
	}
	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
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
	public String getPayinfo() {
		return payinfo;
	}
	public void setPayinfo(String payinfo) {
		this.payinfo = payinfo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public TitanQrCodePayResponse convert2TitanResult(){
		TitanQrCodePayResponse titanQrCodePayResponse = new TitanQrCodePayResponse();
		
		return titanQrCodePayResponse;
	}

}
