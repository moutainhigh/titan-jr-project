package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class CommonPayMethodDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer commonpaymethodid;
	private Integer deskid;
	private Integer paytype;
	private String creator;
	private Date createtime;
	private Integer count;
	private String bankname;
	private String bankmark;
	
	public Integer getCommonpaymethodid() {
		return commonpaymethodid;
	}
	public void setCommonpaymethodid(Integer commonpaymethodid) {
		this.commonpaymethodid = commonpaymethodid;
	}
	public Integer getDeskid() {
		return deskid;
	}
	public void setDeskid(Integer deskid) {
		this.deskid = deskid;
	}
	public Integer getPaytype() {
		return paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankmark() {
		return bankmark;
	}
	public void setBankmark(String bankmark) {
		this.bankmark = bankmark;
	}
	
}
