package com.fangcang.titanjr.web.pojo;

/**
 * 机构查询条件
 * @author luoqinglong
 * @2016年6月28日
 */
public class OrgPojo extends BasePojo {
	private String orgName;
	private String userLoginName;
	/**
	 * 用户类型：1-企业用户，2个人用户
	 */
	private Integer userType;
	
	/**
	 * 审核状态：FT-待审核,FT_INVALID-初审未通过,REVIEW-复审中,REVIEW_INVALID-复审未通过,PASS-已通过
	 */
	private String resultKey;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getResultKey() {
		return resultKey;
	}
	public void setResultKey(String resultKey) {
		this.resultKey = resultKey;
	}
	
}
