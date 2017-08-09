package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgMapInfoDao;
import com.fangcang.titanjr.entity.TitanOrgMapInfo;
import com.fangcang.titanjr.entity.parameter.TitanOrgMapInfoParam;

public class TitanOrgMapInfoDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgMapInfoDao {

	@Override
	public int insert(TitanOrgMapInfo entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgMapInfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanOrgMapInfo> queryList(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException {
		return super.selectList("com.fangcang.titanjr.dao.TitanOrgMapInfoDao.queryList", titanOrgMapInfoParam);
	}

}
