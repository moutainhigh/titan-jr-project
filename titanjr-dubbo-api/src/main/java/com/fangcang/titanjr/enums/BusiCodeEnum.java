package com.fangcang.titanjr.enums;

public enum BusiCodeEnum {
    MerchantOrder("101","商户订单"),
    QueryOrder("102","查询订单"),
    MerchantRefund("103","商户退款"),
    QueryRefund("104","查询退款"),
	QUERY_CARDBIN("106", "卡BIN查询"),
	QUERY_QUICK_CARDBIN("108", "快捷支付卡BIN查询"),
	RECHARGE_CONFIRM("109", "充值确认"),
	QUERY_BANKCARD_BIND("111", "绑卡信息查询"),
	BANKCARD_UNBIND("112", "解绑卡"),
	UPDATE_PHONE("113", "更改预留手机号"),
	CARE_SCEURITY_VERIFY("114", "卡密校验请求");
	
	private String key;
	
	private String value;
	
	private BusiCodeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static BusiCodeEnum getEnumByKey(String key) {
		for (BusiCodeEnum bte : BusiCodeEnum.values()) {
			if (bte.key.equals(key)) {
				return bte;
			}
		}
		return null;
	}
	
	public static String getKeyByValue(String value) {
		String key = "";
		for(BusiCodeEnum buisCodeEnum : BusiCodeEnum.values()) {
			if(buisCodeEnum.value .equals(value)) {
				key = buisCodeEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(String key) {
		String value = null;
		for(BusiCodeEnum buisCodeEnum : BusiCodeEnum.values()) {
			if(buisCodeEnum.key == key) {
				value = buisCodeEnum.value;
				break;
			}
		}
		return value;
	}
	
	/**
	 * 判断key是否在指定枚举集合中
	 */
	public static boolean between(String key,BusiCodeEnum... enums){
		if(key == null){
			return false;
		}
		
		if(enums == null){
			return false;
		}
		
		for(BusiCodeEnum e : enums){
			if(key.equals(e.getKey())){
				return true;
			}
		}
		
		return false;
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
	
	public String toString(){
		return "BusiCodeEnum{key:"+this.key+",value:\""+this.value+"\"}";
	}
}
