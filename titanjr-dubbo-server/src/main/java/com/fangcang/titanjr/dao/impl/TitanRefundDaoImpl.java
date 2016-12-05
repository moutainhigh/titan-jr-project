package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.entity.TitanRefund;

public class TitanRefundDaoImpl  extends GenericDAOMyBatisImpl implements TitanRefundDao{

	@Override
	public int insert(TitanRefund entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanRefundDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	

}
