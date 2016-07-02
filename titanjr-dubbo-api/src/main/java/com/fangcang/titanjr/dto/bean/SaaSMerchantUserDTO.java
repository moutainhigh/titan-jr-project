package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * SAAS 用户信息
 * @author luoqinglong
 * @2016年6月17日
 */
public class SaaSMerchantUserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3951880919145341101L;

	/**
	 * 用户Id
	 */
	private long userId;
	
	/**
	 * 用户登录名
	 */
	private String userLoginName;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
	private String roleName;
	
	private String positionName;
	/**
	 * 手机号码
	 */
	private String mobileNum;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 是否有效
	 */
	private int active;
	
	/**
	 *  是否接收短信
	 */
	private Integer isSMS;
	
	/**
	 * 用户头像地址
	 */
	private String userImg;
	
	/**
	 * 是否已经添加到金服系统
	 */
	private int isAddTfs;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Integer getIsSMS() {
		return isSMS;
	}

	public void setIsSMS(Integer isSMS) {
		this.isSMS = isSMS;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public int getIsAddTfs() {
		return isAddTfs;
	}

	public void setIsAddTfs(int isAddTfs) {
		this.isAddTfs = isAddTfs;
	} 
	
}
