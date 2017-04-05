package com.fangcang.titanjr.common.enums;

/**
 * 消息交互码定义
 * 
 * @author Administrator
 *
 */
public enum TitanMsgCodeEnum {

	// 成功
	TITAN_SUCCESS(0, "成功"),

	// 失败
	TITAN_FAIL(-1, "失败"),

	// 收银台统一 110开通
	// 参数错误
	PARAMETER_VALIDATION_FAILED(110100001, "请求参数错误，请确认！"),

	// 身份认证失败
	AUTHENTITCATION_FAILED(110100002, "身份认证失败，请确认！"),

	// 权限校验
	PERMISSION_CHECK_FAILED(110100003, "该用户没有付款权限，请确认！"),
	
	// 业务订单信息已经发生变化
	BUSS_ORDER_CHANGE_CHECK_FAILED(110100004, "业务订单信息已经发生变化，请重新下单!"),

	// PAY SUCCESS 订单已经付款成功
	PAY_ORDER_SUCCESS(110100005, "订单已经付款成功！"),

	// 收款机构信息错误
	CASHIER_INSTITUTIONS_NOT_EXISTS(110100006, "收款机构不存在，请确认！"),

	// 金融账户不存在
	TITAN_ACCOUNT_NOT_EXISTS(110100007, "金融账户不存在，请确认！"),

	// 收银台不存在，请确认
	CASHIER_DESK_NOT_EXISTS(110100008, "收银台不存在，请确认！"),

	// 收银台不存在，请确认
	PAY_PWD_ERROR(110100009, "付款密码错误，请确认！"),

	// 密码设置失败
	SET_PAY_PWD_ERROR(110100010, "密码设置失败，请确认！"),

	ADD_LOCAL_ORDER_ERROR(110100011, "本地落单失败，请确认！"),

	TRANSFER_FAIL(110100012, "转账失败，请确认！"),

	QUERY_LOCAL_ORDER(110100013, "查询订单失败！"),

	PAY_SUCCESS_NOT_REPEATE(110100014, "支付成功，请勿重复支付！"),

	// 使用新卡提现删除原绑定卡失败
	USE_NEW_CARD_WITHDRAW_DEL_OLD_CARD_FAIL(110100015, "使用新卡提现删除原绑定卡失败"),

	USE_NEW_CARD_WITHDRAW_BING_CARD_FAIL(110100016, "使用新卡提现,绑定新提现卡失败"),
	
	// "提现操作失败，请联系管理员"
	WITHDRAW_OPT_FAIL(110100017, "提现操作失败，请联系管理员"),
	
	// "手续费不能大于提现金额！
	RATE_NOT_MORE_WITHDRAW(110100018, "手续费不能大于提现金额！"),
	
	QR_EXCEPTION(110100018,"调用网关接口异常"),
	
	NO_PERMISSION(110100019,"该用户无访问权限"),
	
    RS_ADD_ORDER_FAIL(110100020,"融数下单失败！"),
	
	RS_ADD_REFUND_ORDER_FAIL(110100021,"下退款单失败！"),
    
    RS_NOTIFY_REFUND_FAIL(110100022,"通知退款失败！"),
    
    LOCAL_ADD_TRANSFER_FAIL(110100023,"退款保存转账单失败！"),
    
    REFUND_TRANSFER_FAIL(110100024,"退款转账单失败！"),
    
    REFUND_SUCCESSED(110100025,"退款已完成，请勿重复退款！"),
    
    PACKAGE_REFUND_PARAM_FAIL(110100026,"封装网关退款参数失败"),
    
    TRANSFER_SUCCESS_UPDATE_LOACL_FAIL(110100027,"退款转账成功，更新本地单失败！"),
    
    
    ORDER_NOT_REFUND(110100028,"该订单不存在或者未支付成功，不能退款"),
    
    ACCOUNT_BALANCE_NOT_ENOUGH(110100029,"账户余额不足，请充值"),
    
    BALANCE_ACCOUNT_NOT_ENOUGH(110100030,"收益子账户余额不足!"),
    
    REFUND_FAIL(110100031,"退款失败"),
    
  //订单信息不存在
    QUERY_ORDER_FAIL(110100032,"订单不存在！"),
    
   RS_SUCCESS_SAVELOCA_FAIL(110100033,"融数下单成功，本地保存失败"),
    
    PACKAGE_RECHARGE_DATA_FAIL(110100034,"封装网关数据失败"),
    
    ACCOUNT_NOT_EXIST(110100035,"该账户未注册！"),
  //落单+绑卡失败
    OPER_ORDER_AND_BIND_CARD_FAIL(110100036,"落单绑卡失败！"),
	
    ORDER_REFUNND_IN_PROCESS(110100037,"该订单正在退款，请勿重复退款"),
    
    ORDER_OUT_TIME(110100038,"该订单已超出退款时间"),
    
    REFUND_UNFREEZE_FAIL(110100039,"资金解冻失败"),
    
 // 权限校验
 	PERMISSION_CHECK_REFUND(110100041, "您还没有付款（退款）权限，请与管理员联系！"),

    REFUND_CONCERT_FAIL(110100040,"商家未绑定金融账户或员工未绑定金融用户"),
    
    LOAN_REQUEST_FAIL(110100042,"请求贷款失败，请联系管理员！"),


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
