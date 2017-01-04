package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.util.StringUtil;

/**
 * 终止贷款
 * @author luoqinglong
 * @2016年11月8日
 */
public class StopLoanRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1802098610487068884L;
	/**
	 * 机构号
	 */
	@NotNull
	private String rootinstcd;
	/**	商户贷款申请订单号
	 * 
	 */
	@NotNull
	private String userorderid;
	/**
	 * 	2. 续议 0.拒绝
	 */
	@NotNull
	private Integer oper;
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
		RequestValidationUtil.check(this);
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

	public String getUserorderid() {
		return userorderid;
	}


	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}


	public Integer getOper() {
		return oper;
	}

	public void setOper(Integer oper) {
		this.oper = oper;
	}
	
}
