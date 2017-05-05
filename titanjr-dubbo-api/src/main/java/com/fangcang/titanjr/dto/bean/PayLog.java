package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 业务日志记录
 * @author luoqinglong
 * @date   2017年4月17日
 */
public class PayLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6842177308243977970L;
	
	@JSONField(name="trans_orderid")
	private String transOrderid;
	
	@JSONField(name="business_type")
	private String businessType;
	
	@JSONField(name="business_des")
	private String businessDes;
	
	@JSONField(name="step_info")
	private String stepInfo;
	
	@JSONField(name="extra_info")
	private String extraInfo;
	
	@JSONField(name="create_time")
	private Date createTime;
	
	private String tableName = "t_titan_pay_log";

	public String getTransOrderid() {
		return transOrderid;
	}

	public void setTransOrderid(String transOrderid) {
		this.transOrderid = transOrderid;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessDes() {
		return businessDes;
	}

	public void setBusinessDes(String businessDes) {
		this.businessDes = businessDes;
	}

	public String getStepInfo() {
		return stepInfo;
	}

	public void setStepInfo(String stepInfo) {
		this.stepInfo = stepInfo;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
