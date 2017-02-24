package com.fangcang.titanjr.entity.parameter;


import java.util.Date;

import com.fangcang.titanjr.entity.TitanTransOrder;

/**
 * TitanTransOrder entity. @author MyEclipse Persistence Tools
 */

public class TitanTransOrderParam extends TitanTransOrder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 559716364003306555L;
	
	private String status;
	
	private Date startTime;
	
	private Date endTime;

	private String partner;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
}