package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanFundFreezereqDao;
import com.fangcang.titanjr.dao.TitanFundUnFreezereqDao;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;
import com.fangcang.titanjr.entity.parameter.TitanUnFundFreezereqParam;

public class TitanFundUnFreezereqDaoImpl extends GenericDAOMyBatisImpl implements TitanFundUnFreezereqDao{
	@Override
	public boolean selectForPage(TitanUnFundFreezereqParam condition,
			PaginationSupport<TitanFundUnFreezereq> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanFundUnFreezereqDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanFundUnFreezereq entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanFundUnFreezereqDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanFundUnFreezereq entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanFundUnFreezereqDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}