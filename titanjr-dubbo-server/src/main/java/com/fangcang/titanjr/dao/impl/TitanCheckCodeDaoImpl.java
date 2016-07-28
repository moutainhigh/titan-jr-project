package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCheckCodeDao;
import com.fangcang.titanjr.entity.TitanCheckCode;
import com.fangcang.titanjr.entity.parameter.TitanCheckCodeParam;

public class TitanCheckCodeDaoImpl extends GenericDAOMyBatisImpl implements TitanCheckCodeDao {

	@Override
	public PaginationSupport<TitanCheckCode> selectForPage(TitanCheckCodeParam condition,PaginationSupport<TitanCheckCode> paginationSupport)
			throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanCheckCodeDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public int insert(TitanCheckCode entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanCheckCodeDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanCheckCode entity) {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCheckCodeDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int disableIsactive(TitanCheckCodeParam condition)
			throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCheckCodeDao.disableIsactive", condition);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	
}
