package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;

public class TitanFundUnFreezereq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int fundFreezereqid;
	
	private String rootinstcd;
	
	private String  requesttime;
	
	private String conditioncode;
	
	private String userid;
	
	private Integer tfsuserid;
	
	private String requestno;
	
	public String getRequestno() {
		return requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFundFreezereqid() {
		return fundFreezereqid;
	}

	public void setFundFreezereqid(int fundFreezereqid) {
		this.fundFreezereqid = fundFreezereqid;
	}

	public String getRootinstcd() {
		return rootinstcd;
	}

	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public String getConditioncode() {
		return conditioncode;
	}

	public void setConditioncode(String conditioncode) {
		this.conditioncode = conditioncode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}
}
