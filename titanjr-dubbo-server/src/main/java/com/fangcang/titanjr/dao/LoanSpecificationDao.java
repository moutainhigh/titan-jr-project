package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.titanjr.entity.LoanSpecification;

public interface LoanSpecificationDao {
	/**
	 * 插入
	 * 
	 * @param LoanSpecification
	 * @return
	 */
	int saveLoanSpecification(LoanSpecification loanSpecification);

	/**
	 * 查询
	 * 
	 * @param LoanSpecification
	 * @return
	 */
	List<LoanSpecification> queryLoanSpecification(
			LoanSpecification loanSpecification);

	/**
	 *
	 * @param LoanSpecification
	 * @return
	 */
	int updateLoanSpecification(LoanSpecification loanSpecification);
}
