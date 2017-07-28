package com.fangcang.titanjr.entity;

import java.util.Date;

public class TitanOperateLog implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -863466622321650919L;
	
	private Integer operateId;
	/**
	 * 操作类型
	 */
	private Integer operateType;
	/***
	 * 操作内容
	 */
	private String operateContent;
	/***
	 * 操作时间
	 */
	private Date operateTime;
	/***
	 * 操作人
	 */
	private String operator;

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
