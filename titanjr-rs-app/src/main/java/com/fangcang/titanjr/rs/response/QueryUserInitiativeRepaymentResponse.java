package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.TUserArepayment;

/**
 * 查询主动还款
 * @author luoqinglong
 * @2016年11月8日
 */
public class QueryUserInitiativeRepaymentResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1179921652409714087L;
	
	/**
	 * 用户主动还款对象信息
	 */
	List<TUserArepayment> tUserArepaymentList;

	public List<TUserArepayment> gettUserArepaymentList() {
		return tUserArepaymentList;
	}

	public void settUserArepaymentList(List<TUserArepayment> tUserArepaymentList) {
		this.tUserArepaymentList = tUserArepaymentList;
	}
	
}
