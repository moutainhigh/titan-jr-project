package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class TitanHelpTypeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6816573302625601128L;
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
	
	private String modifior;
	
	private Date modifyTime;

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
