package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.util.StringUtil;

/***
 * 查询应还款信息
 * @author luoqinglong
 * @2016年10月31日
 */
public class QueryBorrowinfoRequest extends  BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2266941927069913137L;
	/**
	 * 	机构码 融数分配
	 */
	@NotNull
	private String rootinstcd;
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

	/**
	 * 兼容父类的constid
	 * @return
	 */
	public String getRootinstcd() {
		if(StringUtil.isValidString(rootinstcd)){
			return rootinstcd;
		}
		return getConstid();
	}

	public void setRootinstcd(String rootinstcd) {
		this.setConstid(rootinstcd);
		this.rootinstcd = rootinstcd;
	}
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
		RequestValidationUtil.checkNotEmpty(getUserid(), "userid");
		RequestValidationUtil.checkNotEmpty(getProductid(), "productid");
	}
	
}
