package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOperateLogDao;
import com.fangcang.titanjr.entity.TitanOperateLog;

public class TitanOperateLogDaoImpl extends GenericDAOMyBatisImpl implements TitanOperateLogDao{

	@Override
	public int insert(TitanOperateLog entity) throws DaoException {
		return super.insertEntity("com.fangcang.titanjr.dao.TitanOperateLogDao.insertEntity", entity);
	}

}
