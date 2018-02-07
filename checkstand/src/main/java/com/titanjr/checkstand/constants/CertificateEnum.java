/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName CertificateEnum.java
 * @author Jerry
 * @date 2018年2月6日 下午2:53:18  
 */
package com.titanjr.checkstand.constants;

/**
 * 证件枚举
 * @author Jerry
 * @date 2018年2月6日 下午2:53:18  
 */
public enum CertificateEnum {
	
	ID_CARD("0", "身份证"), ID_BOOK("1", "户口簿"), PASSPORT("2", "护照"), OFFICER("3", "军官证"), SOLDIERS("4", "士兵证"), 
	GA2DL("5", "港澳居民来往内地通行证"), TW2DL("6", "台湾同胞来往内地通行证"), ID_TEMP("7", "临时身份证"), FOREIGENER("8", "外国人居留证"), 
	POLICE("9", "警官证"), OTHER("X", "其他证件");
	
	public String code;
	public String name;
	
	private CertificateEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

}
