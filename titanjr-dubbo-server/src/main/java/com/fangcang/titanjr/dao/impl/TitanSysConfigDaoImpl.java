package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanSysConfigDao;
import com.fangcang.titanjr.entity.TitanSysConfig;

public class TitanSysConfigDaoImpl extends GenericDAOMyBatisImpl implements TitanSysConfigDao {

	@Override
	public List<TitanSysConfig> querySysConfigList(TitanSysConfig sysConfig)
			throws DaoException {
		return super.selectList("com.fangcang.titanjr.dao.TitanSysConfigDao.querySysConfigList", sysConfig);
	}

	@Override
	public void accessQueryTest() throws DaoException {
		super.selectList("com.fangcang.titanjr.dao.TitanSysConfigDao.accessQueryTest", new Object());
	}
}
