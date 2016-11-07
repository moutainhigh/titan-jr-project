package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/***
 * 查询应还款信息
 * @author luoqinglong
 * @2016年10月31日
 */
public class InterestRepaymentQueryborrowinfoRequest extends  BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2266941927069913137L;
	/**
	 * 用户订单ID
	 */
	@NotNull
	private String userorderid;
	
	
	public String getUserorderid() {
		return userorderid;
	}


	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
	}
	
}
