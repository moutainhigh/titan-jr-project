/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QuickPayBankEnum.java
 * @author Jerry
 * @date 2017年7月25日 下午2:09:29  
 */
package com.fangcang.titanjr.common.enums;

/**
 * 快捷支付支持的银行列表
 * @author Jerry
 * @date 2017年7月25日 下午2:09:29  
 */
public enum QuickPayBankEnum {
	
	QUICK_CMB("CMB", "招商银行"),
	QUICK_BOC("BOC", "中国银行"),
	QUICK_ICBC("ICBC", "中国工商银行"),
	QUICK_CCB("CCB", "中国建设银行"),
	QUICK_CITIC("CITIC", "中信银行"),
	QUICK_ABC("ABC", "中国农业银行"),
	QUICK_CMBC("CMBC", "招商民生银行"),
	QUICK_SPDB("SPDB", "浦发银行"),
	QUICK_COMM("COMM", "交通银行"),
	QUICK_CIB("CIB", "兴业银行"),
	QUICK_CEB("CEB", "中国光大银行");
	
	private String key;
	private String value;
	
	private QuickPayBankEnum(String key, String value) {
		this.key = key;
		this.value = value;
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
