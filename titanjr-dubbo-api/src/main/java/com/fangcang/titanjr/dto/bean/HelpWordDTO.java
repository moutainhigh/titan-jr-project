package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
/**
 * 关键词搜索
 * @author luoqinglong
 * @2016年10月12日
 */
public class HelpWordDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1308142272751304899L;
	private Integer helpId;
	private String helpTitle;
	private String helpContent;
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
	
	
}
