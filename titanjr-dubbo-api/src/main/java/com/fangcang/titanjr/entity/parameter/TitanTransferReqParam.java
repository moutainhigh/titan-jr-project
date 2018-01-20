package com.fangcang.titanjr.entity.parameter;
// default package

import com.fangcang.titanjr.entity.TitanTransferReq;

import java.util.Date;

/**
 * TitanTransferReq entity. @author MyEclipse Persistence Tools
 */

public class TitanTransferReqParam extends TitanTransferReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4561504175358160325L;

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