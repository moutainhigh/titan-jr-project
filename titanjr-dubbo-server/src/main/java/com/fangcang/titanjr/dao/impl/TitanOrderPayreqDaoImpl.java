package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrderPayreqDao;
import com.fangcang.titanjr.entity.TitanOrderPayreq;
import com.fangcang.titanjr.entity.parameter.TitanOrderPayreqParam;

import java.util.List;

public class TitanOrderPayreqDaoImpl extends GenericDAOMyBatisImpl implements TitanOrderPayreqDao{




	@Override
	public boolean selectForPage(TitanOrderPayreqParam condition,
			PaginationSupport<TitanOrderPayreq> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrderPayreqDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public List<TitanOrderPayreq> queryOrderPayRequestList(TitanOrderPayreqParam requestParam) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanOrderPayreqDao.queryOrderPayRequestList", requestParam);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int insert(TitanOrderPayreq entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrderPayreqDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrderPayreq entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrderPayreqDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int updateTitanOrderPayreqByOrderNo(TitanOrderPayreq entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrderPayreqDao.updateTitanOrderPayreqByOrderNo", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	
}