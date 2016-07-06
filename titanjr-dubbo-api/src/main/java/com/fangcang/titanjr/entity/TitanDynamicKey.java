package com.fangcang.titanjr.entity;

import java.io.Serializable;

public class TitanDynamicKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String encryptionkey;

    private String payorderno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getEncryptionkey() {
		return encryptionkey;
	}

	public void setEncryptionkey(String encryptionkey) {
		this.encryptionkey = encryptionkey;
	}

	public String getPayorderno() {
		return payorderno;
	}

	public void setPayorderno(String payorderno) {
		this.payorderno = payorderno;
	}

}