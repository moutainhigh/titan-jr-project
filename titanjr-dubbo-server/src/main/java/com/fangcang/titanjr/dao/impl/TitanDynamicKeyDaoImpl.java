package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanDynamicKeyDao;
import com.fangcang.titanjr.dao.TitanUserBindInfoDao;
import com.fangcang.titanjr.entity.TitanDynamicKey;
import com.fangcang.titanjr.entity.parameter.TitanDynamicKeyParam;

public class TitanDynamicKeyDaoImpl extends GenericDAOMyBatisImpl implements TitanDynamicKeyDao  {

	@Override
	public boolean selectForPage(TitanDynamicKeyParam condition,
			PaginationSupport<TitanDynamicKey> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanDynamicKeyDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanDynamicKey entity) throws DaoException {
		try {
			return	super.insertEntity("com.fangcang.titanjr.dao.TitanDynamicKeyDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanDynamicKey entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanDynamicKeyDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public String selectKeyByPayOrderNo(TitanDynamicKey entity) throws DaoException {
		try {
			return  getSqlSession().selectOne("com.fangcang.titanjr.dao.TitanDynamicKeyDao.selectKeyByPayOrderNo", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int delete(TitanDynamicKey entity) throws DaoException {
		try {
			return getSqlSession().delete("com.fangcang.titanjr.dao.TitanDynamicKeyDao.deleteDynamicKeyByPayOrderNo", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
