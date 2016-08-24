package com.fangcang.titanjr.common.enums;

/**
 * 消息交互码定义
 * 
 * @author Administrator
 *
 */
public enum TitanMsgCodeEnum {

	// 收银台统一 110开通
	// 参数错误
	PARAMETER_VALIDATION_FAILED(110100001, "请求参数错误，请确认！"),

	// 身份认证失败
	AUTHENTITCATION_FAILED(110100002, "身份认证失败，请确认！"),
	
	//权限校验
	PERMISSION_CHECK_FAILED(110100003, "权限校验失败，请确认！"),

	// 身份认证失败Unexpected error
	UNEXPECTED_ERROR(110999999, "发生错误，请联系管理员!");

	private int code;

	private String key;

	TitanMsgCodeEnum(int code, String key) {
		this.code = code;
		this.key = key;
	}

	public String getResMsg() {
		return code + ":" + key;
	}

	public int getCode() {
		return code;
	}

	public String getKey() {
		return key;
	}
}
