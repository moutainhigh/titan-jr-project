/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QuickPayBankEnum.java
 * @author Jerry
 * @date 2017年7月25日 下午2:09:29  
 */
package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

/**
 * 快捷支付支持的银行枚举
 * @author Jerry
 * @date 2017年7月25日 下午2:09:29  
 */
public enum QuickPayBankEnum {
	
	DEPOSIT_CMB("308", "招商银行", "cmb", "10"),
	DEPOSIT_BOC("104", "中国银行", "boc", "10"),
	DEPOSIT_ICBC("102", "工商银行", "icbc", "10"),
	DEPOSIT_CCB("105", "建设银行", "ccb", "10"),
	DEPOSIT_CITIC("302", "中信银行", "citic", "10"),
	DEPOSIT_ABC("103", "农业银行", "abc", "10"),
	DEPOSIT_CMBC("305", "民生银行", "cmbc", "10"),
	DEPOSIT_SPDB("310", "浦发银行", "spdb", "10"),
	DEPOSIT_CIB("309", "兴业银行", "cib", "10"),
	DEPOSIT_CEB("303", "光大银行", "ceb", "10"),
	DEPOSIT_COMM("301", "交通银行", "comm", "10"),
	DEPOSIT_CGB("306", "广东发展银行", "cgb", "10"),
	DEPOSIT_PSBC("403", "邮政储蓄银行", "psbc", "10"),
	DEPOSIT_PINTAN("4105840", "平安银行", "pingan", "10"),
	
	CREDIT_CMB("308", "招商银行", "cmb", "11"),
	CREDIT_BOC("104", "中国银行", "boc", "11"),
	CREDIT_ICBC("102", "工商银行", "icbc", "11"),
	CREDIT_CCB("105", "建设银行", "ccb", "11"),
	CREDIT_CITIC("302", "中信银行", "citic", "11"),
	CREDIT_ABC("103", "农业银行", "abc", "11"),
	CREDIT_CMBC("305", "民生银行", "cmbc", "11"),
	CREDIT_SPDB("310", "浦发银行", "spdb", "11"),
	CREDIT_CIB("309", "兴业银行", "cib", "11"),
	CREDIT_CEB("303", "光大银行", "ceb", "11"),
	CREDIT_HXB("304", "华夏银行", "hxb", "11"),
	CREDIT_CGB("306", "广东发展银行", "cgb", "11"),
	CREDIT_PSBC("403", "邮政储蓄银行", "psbc", "11"),
	CREDIT_PINTAN("4105840", "平安银行", "pingan", "11");
	
	private String bankCode;//银行编码
	private String bankName;//银行名称
	private String bankInfo;//银行标示
	private String cardType;//10-储蓄卡，11-信用卡
	
	private QuickPayBankEnum(String bankCode, String bankName, String bankInfo, String cardType) {
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.bankInfo = bankInfo;
		this.cardType = cardType;
	}
	
	/**
	 * 根据银行卡编码和类型判断是否存在
	 * @author Jerry
	 * @date 2017年7月25日 下午8:06:20
	 */
	public static boolean isExist(String bankCode, String cardType){
		
		if(!StringUtil.isValidString(bankCode) || !StringUtil.isValidString(cardType)){
			return false;
		}
		
		for (QuickPayBankEnum e : QuickPayBankEnum.values()) {
			
			if(e.bankCode.equals(bankCode) && e.cardType.equals(cardType)){
				
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * 根据银行卡编码和类型获取银行标示
	 * @author Jerry
	 * @date 2017年7月26日 下午3:12:14
	 */
	public static String getBankInfo(String bankCode, String cardType){
		
		if(!StringUtil.isValidString(bankCode) || !StringUtil.isValidString(cardType)){
			return null;
		}
		
		for (QuickPayBankEnum e : QuickPayBankEnum.values()) {
			
			if(e.bankCode.equals(bankCode) && e.cardType.equals(cardType)){
				
				return e.bankInfo;
			}
		}
		
		return null;
		
	}
	
	/**
	 * 根据银行标识和卡类型获取银行名称
	 * @author Jerry
	 * @date 2017年8月8日 上午11:43:50
	 */
	public static String getBankName(String bankInfo, String cardType){
		
		if(!StringUtil.isValidString(bankInfo) || !StringUtil.isValidString(cardType)){
			return null;
		}
		
		for (QuickPayBankEnum e : QuickPayBankEnum.values()) {
			
			if(e.bankInfo.equals(bankInfo) && e.cardType.equals(cardType)){
				
				return e.bankName;
			}
		}
		
		return null;
		
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
