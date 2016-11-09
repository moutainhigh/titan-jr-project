package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * 机构授信信息查询
 * @author luoqinglong
 * @2016年10月31日
 */
public class QueryCreditMerchantInfoRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8538111365055367298L;
	/**
	 * 	申请编号
	 */
	@NotNull
	private String userorderid;
	/**
	 * 	机构码 融数分配
	 */
	@NotNull
	private String rootinstcd;
	
	public String getUserorderid() {
		return userorderid;
	}

	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	public String getRootinstcd() {
		return rootinstcd;
	}

	public void setRootinstcd(String rootinstcd) {
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
