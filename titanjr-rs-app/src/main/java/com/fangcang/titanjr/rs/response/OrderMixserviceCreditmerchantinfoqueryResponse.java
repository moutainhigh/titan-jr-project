package com.fangcang.titanjr.rs.response;

/**
 * 机构授信信息查询
 * @author luoqinglong
 * @2016年10月31日
 */
public class OrderMixserviceCreditmerchantinfoqueryResponse extends BaseResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2374857853195997978L;
	/***
	 * 	用户id
	 */
	private String userid;
	/**
	 * 	机构名称
	 */
	private String name;
	/**
	 * 	授信额度
	 */
	private String creditlimit;
	/**
	 * 	授信期限
	 */
	private String creditdeadline;
	/**
	 * 	可用授信额度
	 */
	private String creditavailability;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreditlimit() {
		return creditlimit;
	}
	public void setCreditlimit(String creditlimit) {
		this.creditlimit = creditlimit;
	}
	public String getCreditdeadline() {
		return creditdeadline;
	}
	public void setCreditdeadline(String creditdeadline) {
		this.creditdeadline = creditdeadline;
	}
	public String getCreditavailability() {
		return creditavailability;
	}
	public void setCreditavailability(String creditavailability) {
		this.creditavailability = creditavailability;
	}
	
	
	
}
