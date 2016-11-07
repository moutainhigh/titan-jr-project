package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * 协议确认(参数校验比较特殊)
 * @author luoqinglong
 * @2016年10月31日
 */
public class OrderServiceAgreementconfirmRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9022511743290581641L;
	/***
	 * 分期付款培训协议(userflag 为1时必须)
	 */
	private String urlkeya;
	/**
	 * 	应收账款融资申请书(userflag 为1时必须)
	 */
	private String urlkeyb;
	/**
	 * 	应收账款债权转让通知书回执(userflag 为1时必须)
	 */
	private String urlkeyc;
	/***
	 * 应收账款债权转让通知书(userflag 为1时必须)
	 */
	private String urlkeyd;
	/***
	 * 应收账款申请商户流水
	 */
	@NotNull
	private String userorderid;
	/**
	 * 	用户标识
	 */
	@NotNull
	private String userflag;
	/**
	 * 	机构保理合同(userflag 为2时必须)
	 */
	private String merchanturlkey;
	/**
	 * 	贷款担保函
	 */
	private String murlkeya;
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
	}
	
	public String getUrlkeya() {
		return urlkeya;
	}
	public void setUrlkeya(String urlkeya) {
		this.urlkeya = urlkeya;
	}
	public String getUrlkeyb() {
		return urlkeyb;
	}
	public void setUrlkeyb(String urlkeyb) {
		this.urlkeyb = urlkeyb;
	}
	public String getUrlkeyc() {
		return urlkeyc;
	}
	public void setUrlkeyc(String urlkeyc) {
		this.urlkeyc = urlkeyc;
	}
	public String getUrlkeyd() {
		return urlkeyd;
	}
	public void setUrlkeyd(String urlkeyd) {
		this.urlkeyd = urlkeyd;
	}
	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getUserflag() {
		return userflag;
	}
	public void setUserflag(String userflag) {
		this.userflag = userflag;
	}
	public String getMerchanturlkey() {
		return merchanturlkey;
	}
	public void setMerchanturlkey(String merchanturlkey) {
		this.merchanturlkey = merchanturlkey;
	}
	public String getMurlkeya() {
		return murlkeya;
	}
	public void setMurlkeya(String murlkeya) {
		this.murlkeya = murlkeya;
	}
}
