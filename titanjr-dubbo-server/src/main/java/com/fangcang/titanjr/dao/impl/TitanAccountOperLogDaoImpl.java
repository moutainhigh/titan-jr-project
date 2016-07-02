package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanAccountOperLogDao;
import com.fangcang.titanjr.entity.TitanAccountOperLog;
import com.fangcang.titanjr.entity.parameter.TitanAccountOperLogParam;

public class TitanAccountOperLogDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountOperLogDao{

	@Override
	public boolean selectForPage(TitanAccountOperLogParam condition,
			PaginationSupport<TitanAccountOperLog> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanAccountOperLogDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanAccountOperLog entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanAccountOperLogDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanAccountOperLog entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanAccountOperLogDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}