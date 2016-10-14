package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 帮助
 * @author luoqinglong
 * @2016年9月22日
 */
public class HelpRequest extends BaseRequestDTO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6576920817209281835L;
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
