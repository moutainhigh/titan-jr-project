package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanRateConfigDao;
import com.fangcang.titanjr.entity.TitanRateConfig;
import com.fangcang.titanjr.entity.parameter.TitanRateConfigParam;

import java.util.List;

public class TitanRateConfigDaoImpl extends GenericDAOMyBatisImpl implements TitanRateConfigDao{

	@Override
	public int batchSaveRateConfigs(List<TitanRateConfig> titanRateConfigs) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanRateConfigDao.batchSaveRateConfigs", titanRateConfigs);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanRateConfig entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanRateConfigDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}