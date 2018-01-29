package com.titanjr.checkstand.constants;

/**
 * 操作类型定义：
 * Created by zhaoshan on 2017/11/17.
 */
public enum BusiCodeEnum {

    PAY_REQUEST("101","支付请求"),
    PAY_QUERY("102","支付查询"),
    REFUND_REQUEST("103","退款请求"),
    REFUND_QUERY("104","退款查询"),
    AGENT_TRADE("105","账户交易"),
	CARD_BIN_QUERY("106", "卡BIN查询"),
	QUICK_PAY_CONFIRM("109", "确认支付"),
	RE_SEND_MSG("110", "重发验证码"),
	CARD_BIND_QUERY("111", "绑卡列表查询"),
	CARD_UNBIND("112", "解绑卡"),
	UPDATE_PHONE("113", "更改预留手机号"),
	CARD_AUTH("114", "卡密鉴权");
    
    private BusiCodeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
    
	private String key;
    private String value;
    
    public static BusiCodeEnum getEnumByKey(String key) {
		for (BusiCodeEnum ote : BusiCodeEnum.values()) {
			if (ote.key.equals(key)) {
				return ote;
			}
		}
		return null;
	}
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    
}
