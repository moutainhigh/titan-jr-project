package com.fangcang.titanjr.entity.parameter;
// default package

import com.fangcang.titanjr.entity.TitanOrderPayreq;

import java.util.Date;


/**
 * TitanOrderPayreq entity. @author MyEclipse Persistence Tools
 */

public class TitanOrderPayreqParam  extends TitanOrderPayreq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515033004489732656L;

	//交易单创建起始日期
	private String startTime;

	//交易单创建结束日期
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}