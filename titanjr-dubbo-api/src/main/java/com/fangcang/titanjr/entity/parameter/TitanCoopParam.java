package com.fangcang.titanjr.entity.parameter;

import java.io.Serializable;
/**
 * 合作平台
 * @author luoqinglong
 * @date   2017年2月15日
 */
public class TitanCoopParam implements Serializable {
	/**
	 * 合作平台类型:2-SAAS,4-TTM
	 */
	private Integer coopType;
	/**
	 * 合作平台类型混淆码
	 */
	private String mixcode;
	
	public Integer getCoopType() {
		return coopType;
	}
	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
	public String getMixcode() {
		return mixcode;
	}
	public void setMixcode(String mixcode) {
		this.mixcode = mixcode;
	}
	
	
}
