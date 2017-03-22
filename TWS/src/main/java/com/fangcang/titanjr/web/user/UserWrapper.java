package com.fangcang.titanjr.web.user;

import java.io.Serializable;
import java.util.Set;

public class UserWrapper implements Serializable {
    private static final long serialVersionUID = -492694012608176482L;

    private Integer id;

    private String loginName;

    private String password;

    private String name;

    private String email;

    private String status;

    private String sex;
    
    private String telNum;
    
    private Integer admin;
    
    private String companyCode;
    
    private Set roles;
    
    private String orgInfo;
    
    public String getFullName(){
    	return loginName+"("+name+")";
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public String getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(String orgInfo) {
		this.orgInfo = orgInfo;
	}
}
