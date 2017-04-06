package com.fangcang.titanjr.entity;

public class VirtualOrgRelation {

	private String orgCode;
	private String vorgCode;
	private String bankName;
	private String bankCard;
	private String bankCode;
	private String vorgName;
	
	public String getVorgName() {
		return vorgName;
	}
	public void setVorgName(String vorgName) {
		this.vorgName = vorgName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getVorgCode() {
		return vorgCode;
	}
	public void setVorgCode(String vorgCode) {
		this.vorgCode = vorgCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
}
