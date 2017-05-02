package com.fangcang.titanjr.dao.impl;


import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrderExceptionDao;
import com.fangcang.titanjr.entity.TitanOrderException;
import com.fangcang.titanjr.entity.parameter.TitanOrderExceptionParam;

public class TitanOrderExceptionDaoImpl extends GenericDAOMyBatisImpl implements TitanOrderExceptionDao{

	

	@Override
	public PaginationSupport<TitanOrderException> selectForPage(TitanOrderExceptionParam condition,
			PaginationSupport<TitanOrderException> paginationSupport) throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanOrderExceptionDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return paginationSupport;
	}

	@Override
	public int insertTitanOrderException(TitanOrderException titanOrderException) throws DaoException{
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrderExceptionDao.insertOrderException", titanOrderException);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateTitanOrderException(TitanOrderException titanOrderException) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrderExceptionDao.updateOrderException", titanOrderException);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	

}
