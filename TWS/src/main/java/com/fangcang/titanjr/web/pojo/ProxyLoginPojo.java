package com.fangcang.titanjr.web.pojo;

/**
 * 代理登录
 * @author luoqinglong
 * @date   2017年2月16日
 */
public class ProxyLoginPojo {
	/**
	 * 合作方混淆码
	 */
	private String channel;
	/**
	 * 加密信息
	 */
	private String info;
	/**
	 * 加密类型
	 */
	private String encrypt_type;
	/**
	 * 请求时间，毫秒
	 */
	private String reqtime;
	/**
	 * 登录后跳转的地址
	 */
	private String jumpurl;
	/***
	 * 签名
	 */
	private String sign;
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getEncrypt_type() {
		return encrypt_type;
	}
	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}
	public String getReqtime() {
		return reqtime;
	}
	public void setReqtime(String reqtime) {
		this.reqtime = reqtime;
	}
	public String getJumpurl() {
		return jumpurl;
	}
	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
