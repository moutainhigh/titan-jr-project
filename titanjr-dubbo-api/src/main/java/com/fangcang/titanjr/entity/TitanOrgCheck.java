package com.fangcang.titanjr.entity;
// default package

import java.util.Date;


public class TitanOrgCheck implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1479153847311192434L;
	private Integer checkid;
	private String constid;
	private String userid;
	private String resultkey;
	private String resultmsg;
	private String checkuser;
	private Date checktime;
	private String creator;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TitanOrgCheck() {
	}

	/** full constructor */
	public TitanOrgCheck(String constid, String userid, String resultkey,
			String resultmsg, String checkuser, Date checktime, String creator,
			Date createtime) {
		this.constid = constid;
		this.userid = userid;
		this.resultkey = resultkey;
		this.resultmsg = resultmsg;
		this.checkuser = checkuser;
		this.checktime = checktime;
		this.creator = creator;
		this.createtime = createtime;
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

	public String getCheckuser() {
		return this.checkuser;
	}

	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}

	public Date getChecktime() {
		return this.checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}