package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgCheckLogDao;
import com.fangcang.titanjr.entity.TitanOrgCheckLog;

public class TitanOrgCheckLogDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgCheckLogDao {

	 

	@Override
	public int insert(TitanOrgCheckLog entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgCheckLogDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
