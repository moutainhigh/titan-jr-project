package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanWithDrawReqDao;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;

public class TitanWithDrawReqDaoImpl extends GenericDAOMyBatisImpl implements TitanWithDrawReqDao{
	@Override
	public boolean selectForPage(TitanWithDrawReqParam condition,PaginationSupport<TitanWithDrawReq> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanWithDrawReqDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return true;
	}

	@Override
	public int insert(TitanWithDrawReq entity) throws DaoException {
		try {
			return	super.insertEntity("com.fangcang.titanjr.dao.TitanWithDrawReqDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanWithDrawReq entity) {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanWithDrawReqDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}