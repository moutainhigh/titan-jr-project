package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class AccountFreezeRequest extends BaseRequest  {
	//===============以下信息必填==========
	//用户类型(1：商户，2：普通用户)
	private String usertype;          
	
	//===============以下信息可选==========
	//角色号
	private String role;
	
	//第三方账户ID (主账户不填)
	private String referuserid;
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getUsertype(), "usertype");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        
        //校验长度
        RequestValidationUtil.checkMaxLength(this.usertype, 1, "usertype");
	}
	
	public String getUsertype() {
		return usertype;
	}
	
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReferuserid() {
		return referuserid;
	}

	public void setReferuserid(String referuserid) {
		this.referuserid = referuserid;
	}


}
