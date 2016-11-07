package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * 机构授信信息查询
 * @author luoqinglong
 * @2016年10月31日
 */
public class OrderMixserviceCreditmerchantinfoqueryRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8538111365055367298L;
	/**
	 * 	申请编号
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
