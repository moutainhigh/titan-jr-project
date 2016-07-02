package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class OrganImageRequest extends BaseRequestDTO{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003629158433840309L;
	private Integer imageid;
	private String userid;
	private String orgcode;
	private Integer imagetype;
	private Integer sizetype;
	private Integer isactive;
	
	public Integer getImageid() {
		return imageid;
	}
	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public Integer getImagetype() {
		return imagetype;
	}
	public void setImagetype(Integer imagetype) {
		this.imagetype = imagetype;
	}
	public Integer getSizetype() {
		return sizetype;
	}
	public void setSizetype(Integer sizetype) {
		this.sizetype = sizetype;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	
	
}
