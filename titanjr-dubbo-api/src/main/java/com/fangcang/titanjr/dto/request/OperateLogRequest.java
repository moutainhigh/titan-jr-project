package com.fangcang.titanjr.dto.request;

import java.io.Serializable;
import java.util.Date;

public class OperateLogRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 946362350717957181L;
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
