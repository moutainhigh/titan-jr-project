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
 * 快捷支付支持的银行的信息枚举
 * @author Jerry
 * @date 2017年7月25日 下午2:09:29  
 */
public enum QuickPayBankEnum {
	//储蓄卡
	DEPOSIT_BOC("104", "中国银行", "boc", "10", 20000, 50000),
	DEPOSIT_ABC("103", "农业银行", "abc", "10", 20000, 50000),
	DEPOSIT_ICBC("102", "工商银行", "icbc", "10", 20000, 50000),
	DEPOSIT_CCB("105", "建设银行", "ccb", "10", 20000, 50000),
	DEPOSIT_CIB("309", "兴业银行", "cib", "10", 20000, 50000),
	DEPOSIT_CEB("303", "光大银行", "ceb", "10", 20000, 50000),
	DEPOSIT_CMBC("305", "民生银行", "cmbc", "10", 20000, 50000),
	DEPOSIT_PSBC("403", "邮政储蓄银行", "psbc", "10", 20000, 50000),
	DEPOSIT_COMM("301", "交通银行", "comm", "10", 20000, 50000),
	DEPOSIT_CITIC("302", "中信银行", "citic", "10", 10000, 10000),
	DEPOSIT_PINTAN("307", "平安银行", "pingan", "10", 20000, 50000),
	DEPOSIT_HXB("304", "华夏银行", "hxb", "10", 20000, 50000),
	DEPOSIT_SPDB("310", "浦发银行", "spdb", "10", 20000, 50000),
	DEPOSIT_CGB("306", "广发银行", "cgb", "10", 20000, 50000),
	DEPOSIT_CMB("308", "招商银行", "cmb", "10", 20000, 50000),
	DEPOSIT_BOS("310", "上海银行", "bos", "10", 5000, 5000),
	//信用卡
	CREDIT_BOC("104", "中国银行", "boc", "11", 20000, 20000),
	CREDIT_ABC("103", "农业银行", "abc", "11", 5000, 50000),
	CREDIT_ICBC("102", "工商银行", "icbc", "11", 20000, 50000),
	CREDIT_CCB("105", "建设银行", "ccb", "11", 20000, 50000),
	CREDIT_CIB("309", "兴业银行", "cib", "11", 20000, 50000),
	CREDIT_CEB("303", "光大银行", "ceb", "11", 20000, 50000),
	CREDIT_CMBC("305", "民生银行", "cmbc", "11", 20000, 50000),
	CREDIT_PSBC("403", "邮政储蓄银行", "psbc", "11", 20000, 50000),
	CREDIT_CITIC("302", "中信银行", "citic", "11", 20000, 50000),
	CREDIT_PINTAN("307", "平安银行", "pingan", "11", 20000, 50000),
	CREDIT_HXB("304", "华夏银行", "hxb", "11", 20000, 50000),
	CREDIT_SPDB("310", "浦发银行", "spdb", "11", 20000, 50000),
	CREDIT_CGB("306", "广发银行", "cgb", "11", 20000, 50000),
	CREDIT_CMB("308", "招商银行", "cmb", "11", 20000, 50000);
	
	private String bankCode;//银行编码
	private String bankName;//银行名称
	private String bankInfo;//银行标示
	private String cardType;//10-储蓄卡，11-信用卡
	private int singleLimit;//单笔限额
	private int dailyLimit;//单日限额
	
	private QuickPayBankEnum(String bankCode, String bankName, String bankInfo, String cardType, 
			int singleLimit, int dailyLimit) {
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.bankInfo = bankInfo;
		this.cardType = cardType;
		this.singleLimit = singleLimit;
		this.dailyLimit = dailyLimit;
	}
	
	/**
	 * 根据银银行标示和类型判断是否存在
	 * @author Jerry
	 * @date 2017年7月25日 下午8:06:20
	 */
	public static boolean isExist(String bankCode, String cardType){
		
		if(!StringUtil.isValidString(bankCode) || !StringUtil.isValidString(cardType)){
			return false;
		}
		
		for (QuickPayBankEnum e : QuickPayBankEnum.values()) {
			
			if(e.bankInfo.equals(bankCode) && e.cardType.equals(cardType)){
				
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
	
	
	/**
	 * 根据银行标识和类型获取枚举
	 * @author Jerry
	 * @date 2017年9月11日 下午6:18:14
	 * @return
	 */
	public static QuickPayBankEnum getBankEnum(String bankCode, String cardType){
		
		if(!StringUtil.isValidString(bankCode) || !StringUtil.isValidString(cardType)){
			return null;
		}
		
		for (QuickPayBankEnum e : QuickPayBankEnum.values()) {
			
			if(e.bankInfo.equals(bankCode) && e.cardType.equals(cardType)){
				
				return e;
			}
		}
		
		return null;
	}
	
	
	/**
	 * 是否需要校验信用卡的cvv码和有效期
	 * @author Jerry
	 * @date 2017年10月23日 上午11:44:27
	 */
	public static boolean isValidAuth(QuickPayBankEnum quickPayBankEnum){
		
		if(quickPayBankEnum == null){
			return false;
		}
		
		if(quickPayBankEnum == CREDIT_CMB || quickPayBankEnum == CREDIT_CCB || quickPayBankEnum == CREDIT_CGB
				|| quickPayBankEnum == CREDIT_SPDB){
			return false;
		}
		
		return true;
		
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

	public int getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(int singleLimit) {
		this.singleLimit = singleLimit;
	}

	public int getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(int dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

}
