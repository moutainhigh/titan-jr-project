package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.entity.LoanExpiryStat;
import com.fangcang.titanjr.entity.LoanProductAmountStat;
import com.fangcang.titanjr.entity.LoanSevenDaysStat;
import com.fangcang.titanjr.entity.parameter.LoanQueryConditions;

public interface LoanOrderDao {
	/**
	 * 插入
	 * 
	 * @param loanOrder
	 * @return
	 */
	int saveLoanApplyOrder(LoanApplyOrder loanOrder);

	/**
	 * 查询
	 * 
	 * @param loanOrder
	 * @return
	 */
	PaginationSupport<LoanApplyOrder> queryLoanApplyOrder(
			LoanQueryConditions loanQueryConditions,
			PaginationSupport<LoanApplyOrder> paginationSupport);

	/**
	 *
	 * @param loanOrder
	 * @return
	 */
	int updateLoanApplyOrder(LoanApplyOrder loanOrder);

	public LoanExpiryStat queryLoanExpiryStat(String orgCode);

	public List<LoanProductAmountStat> queryLoanProductAmountStat(String orgCode);

	public LoanSevenDaysStat queryLoanSevenDaysStat(String orgCode);
}
