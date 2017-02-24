package com.fangcang.titanjr.entity;
// default package

import java.util.Date;


public class TitanOrgCheckLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1479153847311192434L;
	private Long logid;
	private Integer checkid;
	private String constid;
	private String userid;
	private String resultkey;
	private String resultmsg;
	private String optuser;
	private Date opttime;

	// Constructors

	/** default constructor */
	public TitanOrgCheckLog() {
	}

	// Property accessors

	public Integer getCheckid() {
		return this.checkid;
	}

	public void setCheckid(Integer checkid) {
		this.checkid = checkid;
	}

	public String getConstid() {
		return this.constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getResultkey() {
		return this.resultkey;
	}

	public void setResultkey(String resultkey) {
		this.resultkey = resultkey;
	}

	public String getResultmsg() {
		return this.resultmsg;
	}

	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public String getOptuser() {
		return optuser;
	}

	public void setOptuser(String optuser) {
		this.optuser = optuser;
	}

	public Date getOpttime() {
		return opttime;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}
}