package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCodeDao;
import com.fangcang.titanjr.entity.TitanCode;

public class TitanCodeDaoImpl extends GenericDAOMyBatisImpl implements TitanCodeDao {

	@Override
	public Long getNextTitanCode() throws DaoException {
		TitanCode entity = new TitanCode();
		super.insertEntity("com.fangcang.titanjr.dao.TitanCodeDao.getNextCode", entity);
		return entity.getId();
	}

}
