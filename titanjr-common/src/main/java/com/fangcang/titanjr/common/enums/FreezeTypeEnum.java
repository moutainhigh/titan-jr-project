/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName FreezeTypeEnum.java
 * @author Jerry
 * @date 2017年8月9日 上午11:13:32  
 */
package com.fangcang.titanjr.common.enums;

/**
 * @author Jerry
 * @date 2017年8月9日 上午11:13:32  
 */
public enum FreezeTypeEnum {
	
	UNFREEZE("1", "转账到收款方，不冻结"),
	FREEZE_PAYEE("2", "转账到收款方，资金冻结在收款方"),
	FREEZE_PAYER("3", "不转账，资金冻结在付款方");
	
	private String key;
	private String value;
	
	private FreezeTypeEnum(String key, String value) {
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
