package com.fangcang.titanjr.dto.request;

import java.util.Date;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 帮助类别
 * @author luoqinglong
 * @2016年9月28日
 */
public class HelpTypeRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6006334387434475299L;
	private Integer helpType;
	private String name;
	/**
	 * 类型图标
	 */
	private String iconimg;
	/**
	 * 排序号:1排在第一位
	 */
	private Integer orderNo;
	/**
	 * 是否显示：0-不显示，1-显示
	 */
	private Integer isShow;
	
	
	private Date modifytime;

	public Integer getHelpType() {
		return helpType;
	}

	public void setHelpType(Integer helpType) {
		this.helpType = helpType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconimg() {
		return iconimg;
	}

	public void setIconimg(String iconimg) {
		this.iconimg = iconimg;
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

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
}
