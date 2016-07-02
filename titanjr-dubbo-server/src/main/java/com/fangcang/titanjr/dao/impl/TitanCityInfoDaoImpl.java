package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCityInfoDao;
import com.fangcang.titanjr.entity.TitanCityInfo;
import com.fangcang.titanjr.entity.parameter.TitanCityInfoParam;

public class TitanCityInfoDaoImpl extends GenericDAOMyBatisImpl implements TitanCityInfoDao{
	@Override
	public boolean selectForPage(TitanCityInfoParam condition,
			PaginationSupport<TitanCityInfo> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanCityInfoDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanCityInfo entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanCityInfoDao.insertEntity", entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanCityInfo entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCityInfoDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int insertBatch(List<TitanCityInfo> titanCityInfos) {
		try {
			return getSqlSession().update("com.fangcang.titanjr.dao.TitanCityInfoDao.insertBatch", titanCityInfos);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int deleteTitanCitys() throws DaoException {
		try {
			return getSqlSession().delete("com.fangcang.titanjr.dao.TitanCityInfoDao.deleteTitanCitys");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}