package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.LoanCreditOrderDiscardDao;
import com.fangcang.titanjr.entity.LoanCreditOrderDiscard;


public class LoanCreditOrderDiscardDaoImpl extends GenericDAOMyBatisImpl implements LoanCreditOrderDiscardDao {

	@Override
	public int insert(LoanCreditOrderDiscard entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.LoanCreditOrderDiscardDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	
}
