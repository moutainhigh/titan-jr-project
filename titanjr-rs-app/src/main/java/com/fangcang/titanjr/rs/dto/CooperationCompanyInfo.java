package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 合作企业信息
 */
public class CooperationCompanyInfo  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4771727919641265558L;
	//合作企业名称
    private String cooperationName;
    //年交易额
    private String yearAnnualSale;
    //占总销售额比例
    private String saleProportion;
    //结算方式
    private String settlement;
    //合作年限
    private String cooperationYears;
    //合作关系，分销商 供应商。
    private String cooperation;
    
	public String getCooperationName() {
		return cooperationName;
	}
	public void setCooperationName(String cooperationName) {
		this.cooperationName = cooperationName;
	}
	public String getYearAnnualSale() {
		return yearAnnualSale;
	}
	public void setYearAnnualSale(String yearAnnualSale) {
		this.yearAnnualSale = yearAnnualSale;
	}
	public String getSaleProportion() {
		return saleProportion;
	}
	public void setSaleProportion(String saleProportion) {
		this.saleProportion = saleProportion;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public String getCooperationYears() {
		return cooperationYears;
	}
	public void setCooperationYears(String cooperationYears) {
		this.cooperationYears = cooperationYears;
	}
	public String getCooperation() {
		return cooperation;
	}
	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}
    
}
