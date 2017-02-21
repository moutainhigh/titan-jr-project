package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class ItemBankDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer itemid;
	
	private String bankmark;
	
	private String bankname;
	
	private String bankimage;
	
	private String creator;
	
	private Date createtime;

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getBankmark() {
		return bankmark;
	}

	public void setBankmark(String bankmark) {
		this.bankmark = bankmark;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankimage() {
		return bankimage;
	}

	public void setBankimage(String bankimage) {
		this.bankimage = bankimage;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
