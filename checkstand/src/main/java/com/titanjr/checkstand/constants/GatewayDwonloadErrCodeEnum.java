/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QrCodeTradeTypeEnum.java
 * @author Jerry
 * @date 2018年3月21日 下午5:13:01  
 */
package com.titanjr.checkstand.constants;

import com.fangcang.util.StringUtil;

/**
 * 通联网关支付对账文件下载错误码
 * @author Jerry
 * @date 2018年3月22日 下午5:34:56
 */
public enum GatewayDwonloadErrCodeEnum {
	
	SYSTEM_BUSY("ERRORCODE:001", "系统繁忙.请稍候"),
	MERCHANT_ERROR("ERRORCODE:002", "请传入有效的商户号,结算日期,signMsg"),
	SETTLDATE_ERROR("ERRORCODE:003", "结算日期格式错误"),
	MERCHANT_NOTFUND("ERRORCODE:004", "商户号不存在或者 MD5key没有设置"),
	SOURCE_MD5_ERROR("ERRORCODE:005", "摘要信息验证有误"),
	NO_INFO("ERRORCODE:006", "没有相应的对账信息");
	
	public String key;
	public String value;
	
	private GatewayDwonloadErrCodeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static boolean isExist(String key){
		
		if(!StringUtil.isValidString(key)){
			return false;
		}
		for (GatewayDwonloadErrCodeEnum errCodeEnum : GatewayDwonloadErrCodeEnum.values()) {
			if(key.equals(errCodeEnum.key)){
				return true;
			}
		}
		return false;
		
	}
	
	public static String getValue(String key){
		
		if(!StringUtil.isValidString(key)){
			return null;
		}
		for (GatewayDwonloadErrCodeEnum errCodeEnum : GatewayDwonloadErrCodeEnum.values()) {
			if(key.equals(errCodeEnum.key)){
				return errCodeEnum.value;
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
