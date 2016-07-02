package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanTradeReqFlowDao;
import com.fangcang.titanjr.entity.TitanTradeReqFlow;
import com.fangcang.titanjr.entity.parameter.TitanTradeReqFlowParam;

public class TitanTradeReqFlowDaoImpl extends GenericDAOMyBatisImpl implements TitanTradeReqFlowDao{
	@Override
	public boolean selectForPage(TitanTradeReqFlowParam condition,
			PaginationSupport<TitanTradeReqFlow> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanTradeReqFlowDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanTradeReqFlow entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanTradeReqFlowDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanTradeReqFlow entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanTradeReqFlowDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}