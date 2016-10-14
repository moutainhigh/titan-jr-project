package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class TitanHelpDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5001998572484642086L;
	private Integer helpId;
	private String helpTitle;
	private String helpContent;
	/**
	 * 类型
	 */
	private Integer helpType;
	/**
	 * 排序号:1排在第一位
	 */
	private Integer orderNo;
	/**
	 * 是否显示：0-不显示，1-显示
	 */
	private Integer isShow;
	private String creator;
	private Date createTime;
	private String modifior;
	private Date modifyTime;
	public Integer getHelpId() {
		return helpId;
	}
	public void setHelpId(Integer helpId) {
		this.helpId = helpId;
	}
	public String getHelpTitle() {
		return helpTitle;
	}
	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}
	public String getHelpContent() {
		return helpContent;
	}
	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}
	public Integer getHelpType() {
		return helpType;
	}
	public void setHelpType(Integer helpType) {
		this.helpType = helpType;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifior() {
		return modifior;
	}
	public void setModifior(String modifior) {
		this.modifior = modifior;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
