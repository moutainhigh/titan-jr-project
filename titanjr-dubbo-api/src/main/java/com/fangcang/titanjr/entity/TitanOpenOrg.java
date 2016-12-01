package com.fangcang.titanjr.entity;

public class TitanOpenOrg  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	//金融用户
	private String userid;
	
	//rsa私钥
	private String privatekey;
	
	//公钥
	private String publicKey;
	
	//rsa的 module 进制16
	private String module;
	
	//
	private String empoent;
	
	//商户编码
	private String merchcode;
	
	//前缀
	private String prefix;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getEmpoent() {
		return empoent;
	}

	public void setEmpoent(String empoent) {
		this.empoent = empoent;
	}

	public String getMerchcode() {
		return merchcode;
	}

	public void setMerchcode(String merchcode) {
		this.merchcode = merchcode;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
