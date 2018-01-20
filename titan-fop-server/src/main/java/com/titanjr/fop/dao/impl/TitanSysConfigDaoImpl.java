package com.titanjr.fop.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.titanjr.fop.dao.TitanSysConfigDao;
import com.titanjr.fop.entity.TitanSysConfig;

public class TitanSysConfigDaoImpl extends GenericDAOMyBatisImpl implements TitanSysConfigDao {

	@Override
	public List<TitanSysConfig> querySysConfigList(TitanSysConfig sysConfig)
			throws DaoException {
		return super.selectList("com.titanjr.fop.dao.TitanSysConfigDao.querySysConfigList", sysConfig);
	}
}
