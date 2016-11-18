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
	
	//rsa公钥
	private String publickey;
	
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

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
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
	
}
