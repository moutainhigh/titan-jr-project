package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgImageDao;
import com.fangcang.titanjr.entity.TitanOrgImage;
import com.fangcang.titanjr.entity.parameter.TitanOrgImageParam;

public class TitanOrgImageDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgImageDao{
	@Override
	public PaginationSupport<TitanOrgImage> selectForPage(TitanOrgImageParam condition,
			PaginationSupport<TitanOrgImage> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanOrgImageDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public int insert(TitanOrgImage entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgImageDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrgImage entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgImageDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	

	@Override
	public int delete(TitanOrgImageParam param) throws DaoException {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanOrgImageDao.deleteEntity", param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateIsActiveByOrgCode(TitanOrgImage entity)
			throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgImageDao.updateIsActiveByOrgCode", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}