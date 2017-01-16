package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.util.StringUtil;

/**
 * 主动还款
 * @author luoqinglong
 * @2016年11月8日
 */
public class UserInitiativeRepamentRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9111364358155878818L;
	/**
	 * 机构码
	 */
	@NotNull
	private String rootinstcd;
	/**
	 * 	用户订单id
	 */
	@NotNull
	private String userorderid;
	
	/**
	 * 利息还款金额
	 */
	private String realinterestamount;
	/**
	 * 本金还款金额
	 */
	private String realcapitalamount;
	/**
	 * 	逾期利息还款金额
	 */
	private String realoverdueinterestamount;
	/**
	 * 逾期本金还款金额
	 */
	private String realoverduecapitalamount;
	
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
		RequestValidationUtil.checkNotEmpty(getUserid(), "userid");
		RequestValidationUtil.checkNotEmpty(getProductid(), "productid");
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

	public String getRealinterestamount() {
		return realinterestamount;
	}

	public void setRealinterestamount(String realinterestamount) {
		this.realinterestamount = realinterestamount;
	}

	public String getRealcapitalamount() {
		return realcapitalamount;
	}

	public void setRealcapitalamount(String realcapitalamount) {
		this.realcapitalamount = realcapitalamount;
	}

	public String getRealoverdueinterestamount() {
		return realoverdueinterestamount;
	}

	public void setRealoverdueinterestamount(String realoverdueinterestamount) {
		this.realoverdueinterestamount = realoverdueinterestamount;
	}

	public String getRealoverduecapitalamount() {
		return realoverduecapitalamount;
	}

	public void setRealoverduecapitalamount(String realoverduecapitalamount) {
		this.realoverduecapitalamount = realoverduecapitalamount;
	}
	
}
