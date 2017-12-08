package com.fangcang.titanjr.entity.parameter;

// default package

import com.fangcang.titanjr.entity.TitanWithDrawReq;

import java.util.Date;

/**
 * TitanWithDrawReq entity. @author MyEclipse Persistence Tools
 */

public class TitanWithDrawReqParam extends TitanWithDrawReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 877817067945055889L;

	private Date startTime;

	private Date endTime;

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
}