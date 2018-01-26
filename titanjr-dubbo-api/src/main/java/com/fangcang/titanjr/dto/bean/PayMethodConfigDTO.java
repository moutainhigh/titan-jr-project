package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;


public class PayMethodConfigDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//对应于payMethod表中的key，外键关联',
	private Integer paymethodid;
	//'配置给金服平台哪个商家使用',
	private String userid;
	//'前台页面通知地址'
	private String pageurl;
	//'后台服务通知地址'
	private String notifyurl;
	//'创建人'
	private String createor;
	//'创建时间'
	private Date createTime;
	
	//通联网银支付前台回调地址
	private String tl_NetBankPay_ConfirmPageurl;
	//通联网银支付后台通知地址
	private String tl_NetBankPay_Notifyurl;
	//通联扫码支付后台通知地址
	private String tl_QrCodePay_Notifyurl;
	//通联微信公众号支付后台通知地址
	private String tl_WechatPay_Notifyurl;
	//融宝快捷支付后台通知地址
	private String rb_QuickPay_Notifyurl;
	
	
	public Integer getPaymethodid() {
		return paymethodid;
	}
	public void setPaymethodid(Integer paymethodid) {
		this.paymethodid = paymethodid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	public String getNotifyurl() {
		return notifyurl;
	}
	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}
	public String getCreateor() {
		return createor;
	}
	public void setCreateor(String createor) {
		this.createor = createor;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTl_NetBankPay_ConfirmPageurl() {
		return tl_NetBankPay_ConfirmPageurl;
	}
	public void setTl_NetBankPay_ConfirmPageurl(String tl_NetBankPay_ConfirmPageurl) {
		this.tl_NetBankPay_ConfirmPageurl = tl_NetBankPay_ConfirmPageurl;
	}
	public String getTl_NetBankPay_Notifyurl() {
		return tl_NetBankPay_Notifyurl;
	}
	public void setTl_NetBankPay_Notifyurl(String tl_NetBankPay_Notifyurl) {
		this.tl_NetBankPay_Notifyurl = tl_NetBankPay_Notifyurl;
	}
	public String getTl_QrCodePay_Notifyurl() {
		return tl_QrCodePay_Notifyurl;
	}
	public void setTl_QrCodePay_Notifyurl(String tl_QrCodePay_Notifyurl) {
		this.tl_QrCodePay_Notifyurl = tl_QrCodePay_Notifyurl;
	}
	public String getTl_WechatPay_Notifyurl() {
		return tl_WechatPay_Notifyurl;
	}
	public void setTl_WechatPay_Notifyurl(String tl_WechatPay_Notifyurl) {
		this.tl_WechatPay_Notifyurl = tl_WechatPay_Notifyurl;
	}
	public String getRb_QuickPay_Notifyurl() {
		return rb_QuickPay_Notifyurl;
	}
	public void setRb_QuickPay_Notifyurl(String rb_QuickPay_Notifyurl) {
		this.rb_QuickPay_Notifyurl = rb_QuickPay_Notifyurl;
	}

}
