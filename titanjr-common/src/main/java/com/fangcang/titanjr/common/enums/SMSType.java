package com.fangcang.titanjr.common.enums;

/**
 * 多媒体信息类型
 * @author luoqinglong
 * @2016年7月6日
 */
public enum SMSType {
	REG_CODE(1,"用户注册时的验证码"),PAY_PASSWORD_MODIFY(2,"修改付款密码时的验证码"),LOGIN_PASSWORD_MODIFY(3,"修改登录密码时的验证码");
	
	private SMSType(int type,String des){
		this.type = type;
		this.des = des;
	}
	private int type;
	private String des;
	public int getType() {
		return type;
	}
	public String getDes() {
		return des;
	}
	
}
