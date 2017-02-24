package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrderExceptionDao;
import com.fangcang.titanjr.entity.TitanOrderException;

public class TitanOrderExceptionDaoImpl extends GenericDAOMyBatisImpl implements TitanOrderExceptionDao{

	@Override
	public List<TitanOrderException> selectTitanOrderException(
			TitanOrderException titanOrderException) {
		
		return null;
	}

	@Override
	public int insertTitanOrderException(TitanOrderException titanOrderException) throws DaoException{
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrderExceptionDao.insertOrderException", titanOrderException);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
