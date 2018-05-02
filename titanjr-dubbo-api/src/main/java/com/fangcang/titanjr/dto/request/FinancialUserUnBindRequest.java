package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 解绑用户
 * @author luoqinglong
 * @2016年5月18日
 */
public class FinancialUserUnBindRequest extends BaseRequestDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5835979192155353391L;
	
	private Integer tfsuserid;
	private String merchantcode;
	/**
	 * 合作方类型
	 */
	private Integer coopType;
	
	
	public String getMerchantcode() {
		return merchantcode;
	}
	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}
	public Integer getTfsuserid() {
		return tfsuserid;
	}
	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}
	public Integer getCoopType() {
		return coopType;
	}
	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
	
}