package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.TUserRepayment;

/**
 * 查询贷款的还款状态及历史
 * @author luoqinglong
 * @2016年11月8日
 */
public class QueryUserRepaymentResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9212386850944658877L;
	/***
	 * 用户还款信息
	 */
	List<TUserRepayment> tUserRepaymentList;

	public List<TUserRepayment> gettUserRepaymentList() {
		return tUserRepaymentList;
	}

	public void settUserRepaymentList(List<TUserRepayment> tUserRepaymentList) {
		this.tUserRepaymentList = tUserRepaymentList;
	}
}
