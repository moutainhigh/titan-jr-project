package com.fangcang.titanjr.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.entity.LoanExpiryStat;
import com.fangcang.titanjr.entity.LoanProductAmountStat;
import com.fangcang.titanjr.entity.LoanSevenDaysStat;
import com.fangcang.titanjr.entity.parameter.LoanQueryConditions;

public class LoanOrderDaoImpl extends GenericDAOMyBatisImpl implements
		LoanOrderDao {

	private static final Log log = LogFactory.getLog(LoanOrderDaoImpl.class);

	@Override
	public int saveLoanApplyOrder(LoanApplyOrder loanApplyOrder) {
		try {
			return super
					.insertEntity(
							"com.fangcang.titanjr.dao.LoanOrderDao.insertLoanApplyOrder",
							loanApplyOrder);
		} catch (Exception e) {
			log.error("saveLoanApplyOrder Error", e);
			throw new DaoException(e);
		}
	}

	public LoanExpiryStat queryLoanExpiryStat(String orgCode) {
		try {
			return (LoanExpiryStat) super
					.selectOne(
							"com.fangcang.titanjr.dao.LoanOrderDao.queryLoanExpiryStat",
							orgCode);
		} catch (Exception e) {
			log.error("queryLoanExpiryStat Error", e);
			throw new DaoException(e);
		}
	}

	public List<LoanProductAmountStat> queryLoanProductAmountStat(String orgCode) {
		try {
			return super
					.selectList(
							"com.fangcang.titanjr.dao.LoanOrderDao.queryProductTypeAmount",
							orgCode);
		} catch (Exception e) {
			log.error("queryLoanProductAmountStat Error", e);
			throw new DaoException(e);
		}
	}

	public LoanSevenDaysStat queryLoanSevenDaysStat(String orgCode) {
		try {
			return (LoanSevenDaysStat) super
					.selectOne(
							"com.fangcang.titanjr.dao.LoanOrderDao.queryLoanSevenDaysStat",
							orgCode);
		} catch (Exception e) {
			log.error("queryLoanSevenDaysStat Error", e);
			throw new DaoException(e);
		}
	}

	
	@Override
	public List<LoanApplyOrder> listLoanApplyOrder(
			LoanQueryConditions loanQueryConditions) {
		
		try {
			return super.selectList("com.fangcang.titanjr.dao.LoanOrderDao.queryLoanApplyOrder",loanQueryConditions);
		} catch (Exception e) {
			log.error("listLoanApplyOrder Error,loanQueryConditions:"+Tools.gsonToString(loanQueryConditions), e);
			throw new DaoException(e);
		}
	}

	@Override
	public PaginationSupport<LoanApplyOrder> queryLoanApplyOrder(
			LoanQueryConditions loanQueryConditions,
			PaginationSupport<LoanApplyOrder> paginationSupport) {
		try {
			return super
					.selectForPage(
							"com.fangcang.titanjr.dao.LoanOrderDao.queryLoanApplyOrder",
							loanQueryConditions, paginationSupport);
		} catch (Exception e) {
			log.error("queryLoanApplyOrder Error", e);
			throw new DaoException(e);
		}
	}

	@Override
	public int updateLoanApplyOrder(LoanApplyOrder loanApplyOrder) {
		try {
			return super
					.updateEntity(
							"com.fangcang.titanjr.dao.LoanOrderDao.updateLoanApplyOrder",
							loanApplyOrder);
		} catch (Exception e) {
			log.error("updateLoanApplyOrder Error", e);
			throw new DaoException(e);
		}
	}
}
