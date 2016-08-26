package com.fangcang.titanjr.common.enums;

/**
 * 消息交互码定义
 * 
 * @author Administrator
 *
 */
public enum TitanMsgCodeEnum {
	
	//成功
	TITAN_SUCCESS(0,"成功"),
	
	// 收银台统一 110开通
	// 参数错误
	PARAMETER_VALIDATION_FAILED(110100001, "请求参数错误，请确认！"),

	// 身份认证失败
	AUTHENTITCATION_FAILED(110100002, "身份认证失败，请确认！"),

	// 权限校验
	PERMISSION_CHECK_FAILED(110100003, "权限校验失败，请确认！"),
	
	// 业务订单信息已经发生变化
	BUSS_ORDER_CHANGE_CHECK_FAILED(110100004, "业务订单信息已经发生变化，请重新下单!"),
	
	//PAY SUCCESS
	PAY_ORDER_SUCCESS(110100005, "订单已经付款成功！"),

	// 身份认证失败Unexpected error
	UNEXPECTED_ERROR(110999999, "发生错误，请联系管理员!");

	private int code;

	private String key;

	TitanMsgCodeEnum(int code, String key) {
		this.code = code;
		this.key = key;
	}
	
	
	public static TitanMsgCodeEnum findTitanMsgCodeEnum(String code) {
		for (TitanMsgCodeEnum titanMsgEnum : values()) {
			if (String.valueOf(titanMsgEnum.getCode()).equals(code)) {
				return titanMsgEnum;
			}
		}
		return null;
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
