/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLQrCodePayCallbackRequest.java
 * @author Jerry
 * @date 2017年12月20日 下午6:25:08  
 */
package com.titanjr.checkstand.request;

import java.io.Serializable;

/**
 * 通联扫码支付结果回调参数对象
 * @author Jerry
 * @date 2017年12月20日 下午6:25:08  
 */
public class TLQrCodePayCallbackRequest implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 5637507531587814268L;
	
	/**
	 * 应用ID
	 */
	private String appid;
	/**
	 * 收银宝平台流水号，通联系统内唯一
	 */
	private String outtrxid;
	/**
	 * 交易类型    VSP501：微信支付    VSP511 : 支付宝支付
	 */
	private String trxcode;
	/**
	 * 收银宝平台流水号，通联系统内唯一
	 */
	private String trxid;
	/**
	 * 交易金额，分
	 */
	private String trxamt;
	/**
	 * 交易请求日期  yyyyMMdd
	 */
	private String trxdate;
	/**
	 * 交易完成时间，yyyyMMddHHmmss
	 */
	private String paytime;
	/**
	 * 渠道交易单号    如支付宝,微信平台订单号
	 */
	private String chnltrxid;
	/**
	 * 交易状态   0000：交易成功，其他为处理中
	 */
	private String trxstatus;
	/**
	 * 商户号
	 */
	private String cusid;
	/**
	 * 终端号
	 */
	private String termno;
	/**
	 * 终端批次号
	 */
	private String termbatchid;
	/**
	 * 终端流水号
	 */
	private String termtraceno;
	/**
	 * 终端授权码
	 */
	private String termauthno;
	/**
	 * 终端参考号
	 */
	private String termrefnum;
	/**
	 * 交易备注
	 */
	private String trxreserved;
	/**
	 * 原交易ID，对于冲正、撤销、退货等交易时填写，针对POS终端交易
	 */
	private String srctrxid;
	/**
	 * 商户订单号
	 */
	private String cusorderid;
	/**
	 * 支付人帐号    例如:微信支付的openid，支付宝平台的user_id<br>
	 * 如果信息为空,则默认填写000000
	 */
	private String acct;
	/**
	 * 
	 */
	private String sign;
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOuttrxid() {
		return outtrxid;
	}
	public void setOuttrxid(String outtrxid) {
		this.outtrxid = outtrxid;
	}
	public String getTrxcode() {
		return trxcode;
	}
	public void setTrxcode(String trxcode) {
		this.trxcode = trxcode;
	}
	public String getTrxid() {
		return trxid;
	}
	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}
	public String getTrxamt() {
		return trxamt;
	}
	public void setTrxamt(String trxamt) {
		this.trxamt = trxamt;
	}
	public String getTrxdate() {
		return trxdate;
	}
	public void setTrxdate(String trxdate) {
		this.trxdate = trxdate;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getChnltrxid() {
		return chnltrxid;
	}
	public void setChnltrxid(String chnltrxid) {
		this.chnltrxid = chnltrxid;
	}
	public String getTrxstatus() {
		return trxstatus;
	}
	public void setTrxstatus(String trxstatus) {
		this.trxstatus = trxstatus;
	}
	public String getCusid() {
		return cusid;
	}
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	public String getTermno() {
		return termno;
	}
	public void setTermno(String termno) {
		this.termno = termno;
	}
	public String getTermbatchid() {
		return termbatchid;
	}
	public void setTermbatchid(String termbatchid) {
		this.termbatchid = termbatchid;
	}
	public String getTermtraceno() {
		return termtraceno;
	}
	public void setTermtraceno(String termtraceno) {
		this.termtraceno = termtraceno;
	}
	public String getTermauthno() {
		return termauthno;
	}
	public void setTermauthno(String termauthno) {
		this.termauthno = termauthno;
	}
	public String getTermrefnum() {
		return termrefnum;
	}
	public void setTermrefnum(String termrefnum) {
		this.termrefnum = termrefnum;
	}
	public String getTrxreserved() {
		return trxreserved;
	}
	public void setTrxreserved(String trxreserved) {
		this.trxreserved = trxreserved;
	}
	public String getSrctrxid() {
		return srctrxid;
	}
	public void setSrctrxid(String srctrxid) {
		this.srctrxid = srctrxid;
	}
	public String getCusorderid() {
		return cusorderid;
	}
	public void setCusorderid(String cusorderid) {
		this.cusorderid = cusorderid;
	}
	public String getAcct() {
		return acct;
	}
	public void setAcct(String acct) {
		this.acct = acct;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
