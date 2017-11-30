package com.titanjr.checkstand.constants;

/**
 * 操作类型定义：
 * 支付请求，支付查询，退款请求，退款查询,账户支付
 * Created by zhaoshan on 2017/11/17.
 */
public enum OperateTypeEnum {

    PAY_REQUEST("1001","支付请求"),
    PAY_QUERY("1002","支付查询"),
    REFUND_REQUEST("1003","退款请求"),
    REFUND_QUERY("1004","退款查询"),
    ACCOUNT_PAY("1005","账户支付");
    
    private OperateTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
    
	private String key;
    private String value;
    
    public static OperateTypeEnum getEnumByKey(String key) {
		for (OperateTypeEnum ote : OperateTypeEnum.values()) {
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
