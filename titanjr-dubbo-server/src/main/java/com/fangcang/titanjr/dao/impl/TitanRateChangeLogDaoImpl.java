package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanRateChangeLogDao;
import com.fangcang.titanjr.entity.TitanRateChangeLog;
import com.fangcang.titanjr.entity.parameter.TitanRateChangeLogParam;

public class TitanRateChangeLogDaoImpl extends GenericDAOMyBatisImpl implements TitanRateChangeLogDao{
	@Override
	public boolean selectForPage(TitanRateChangeLogParam condition,
			PaginationSupport<TitanRateChangeLog> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanRateChangeLogDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanRateChangeLog entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanRateChangeLogDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanRateChangeLog entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanRateChangeLogDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}