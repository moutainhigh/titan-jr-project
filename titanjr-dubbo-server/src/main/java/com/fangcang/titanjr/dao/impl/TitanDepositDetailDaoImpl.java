package com.fangcang.titanjr.dao.impl;

import javax.annotation.Resource;

import com.fangcang.corenut.dao.GenericDAO;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanDepositDetailDao;
import com.fangcang.titanjr.entity.TitanDepositDetail;

public class TitanDepositDetailDaoImpl implements TitanDepositDetailDao {
	
	@Resource(name="baseSqlMapClient")
	private GenericDAO genericDAO;
	
	@Override
	public int insert(TitanDepositDetail entity) throws DaoException {
		
		return genericDAO.insertEntity("com.fangcang.titanjr.dao.TitanDepositDetailDao.insertEntity", entity);
	}

}
