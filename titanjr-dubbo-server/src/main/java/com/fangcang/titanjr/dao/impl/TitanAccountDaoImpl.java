package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanAccountDao;
import com.fangcang.titanjr.entity.TitanAccount;
import com.fangcang.titanjr.entity.TitanUserBindInfo;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;

public class TitanAccountDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountDao {

	@Override
	public PaginationSupport<TitanAccount> selectForPage(TitanAccountParam condition,PaginationSupport<TitanAccount> paginationSupport)
			throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanAccountDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public int insert(TitanAccount entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanAccountDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanAccount entity) {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanAccountDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}
