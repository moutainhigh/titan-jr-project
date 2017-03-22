package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/***
 * 合作平台信息
 * @author luoqinglong
 * @date   2017年2月15日
 */
public class CoopDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6914397224104414373L;
	
	private Integer id;
	/**
	 * 合作方名称
	 */
	private String coopName;
	/**
	 * 合作方:2-SAAS，4-TTM 
	 */
	private Integer coopType;
	/**
	 * coopType的混淆码
	 */
	private String mixcode;
	
	private String publicKey;
	
	private String privateKey;
	/**
	 * 公钥模数
	 */
	private String publicModulus;
	/**
	 * 公钥指数
	 */
	private String publicExponent;
	/**
	 * md5key
	 */
	private String md5Key;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCoopName() {
		return coopName;
	}
	public void setCoopName(String coopName) {
		this.coopName = coopName;
	}
	
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
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicModulus() {
		return publicModulus;
	}
	public void setPublicModulus(String publicModulus) {
		this.publicModulus = publicModulus;
	}
	public String getPublicExponent() {
		return publicExponent;
	}
	public void setPublicExponent(String publicExponent) {
		this.publicExponent = publicExponent;
	}
	public String getMd5Key() {
		return md5Key;
	}
	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}
	
}
