package com.fangcang.titanjr.web.pojo;

import java.io.Serializable;

/***
 * 问题参数
 * @author luoqinglong
 * @2016年10月13日
 */
public class HelpPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -802661323170515100L;
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
}
