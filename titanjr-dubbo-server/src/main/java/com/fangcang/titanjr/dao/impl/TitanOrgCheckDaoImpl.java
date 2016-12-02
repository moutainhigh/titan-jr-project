package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgCheckDao;
import com.fangcang.titanjr.entity.TitanOrgCheck;
import com.fangcang.titanjr.entity.parameter.TitanOrgCheckParam;

public class TitanOrgCheckDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgCheckDao {

	@Override
	public boolean selectForPage(TitanOrgCheckParam condition,
			PaginationSupport<TitanOrgCheck> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrgCheckDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanOrgCheck entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgCheckDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrgCheck entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgCheckDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
