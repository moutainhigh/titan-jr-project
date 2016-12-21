package com.fangcang.titanjr.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanSpecificationDao;
import com.fangcang.titanjr.entity.LoanSpecification;

public class LoanSpecificationDaoImpl extends GenericDAOMyBatisImpl implements
		LoanSpecificationDao {
	private static final Log log = LogFactory
			.getLog(LoanSpecificationDaoImpl.class);

	@Override
	public int saveLoanSpecification(LoanSpecification loanSpecification) {
		try {
			return super
					.insertEntity(
							"com.fangcang.titanjr.dao.LoanSpecificationDao.insertLoanSpecification",
							loanSpecification);
		} catch (Exception e) {
			log.error("saveLoanSpecification Error", e);
			throw new DaoException(e);
		}
	}

	@Override
	public List<LoanSpecification> queryLoanSpecification(
			LoanSpecification loanSpecification) {
		try {
			return super
					.selectList(
							"com.fangcang.titanjr.dao.LoanSpecificationDao.queryLoanSpecification",
							loanSpecification);
		} catch (Exception e) {
			log.error("queryLoanSpecification Error", e);
			throw new DaoException(e);
		}
	}

	@Override
	public int updateLoanSpecification(LoanSpecification loanSpecification) {
		try {
			return super
					.updateEntity(
							"com.fangcang.titanjr.dao.LoanSpecificationDao.updateLoanSpecification",
							loanSpecification);
		} catch (Exception e) {
			log.error("updateLoanSpecification Error", e);
			throw new DaoException(e);
		}
	}

}
