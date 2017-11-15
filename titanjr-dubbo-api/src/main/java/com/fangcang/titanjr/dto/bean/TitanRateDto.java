package com.fangcang.titanjr.dto.bean;

import java.util.Date;

/**
 * 
 * @ClassName: TitanRateInfo
 * @Description: 费率配置详情
 * @author: wengxitao
 * @date: 2016年8月15日 上午11:49:09
 */
public class TitanRateDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer rateconfigid;
	private String userid;
	private String deskId;
	private Integer ratetype;
	private Float standrate;
	private Float executionrate;
	private String description;
	private String creator;
	private Date createtime;
	private Date expiration;
	public Integer getRateconfigid() {
		return rateconfigid;
	}
	public void setRateconfigid(Integer rateconfigid) {
		this.rateconfigid = rateconfigid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public Integer getRatetype() {
		return ratetype;
	}
	public void setRatetype(Integer ratetype) {
		this.ratetype = ratetype;
	}
	public Float getStandrate() {
		return standrate;
	}
	public void setStandrate(Float standrate) {
		this.standrate = standrate;
	}
	public Float getExecutionrate() {
		return executionrate;
	}
	public void setExecutionrate(Float executionrate) {
		this.executionrate = executionrate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
