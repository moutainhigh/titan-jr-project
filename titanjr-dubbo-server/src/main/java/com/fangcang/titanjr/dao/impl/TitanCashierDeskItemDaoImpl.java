package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCashierDeskItemDao;
import com.fangcang.titanjr.entity.TitanCashierDeskItem;


public class TitanCashierDeskItemDaoImpl extends GenericDAOMyBatisImpl implements TitanCashierDeskItemDao{

	@Override
	public int saveCashierDeskItem(TitanCashierDeskItem cashierDeskItem) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanCashierDeskItemDao.saveCashierDeskItem",
					cashierDeskItem);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateCashierDeskItem(TitanCashierDeskItem entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCashierDeskItemDao.updateCashierDeskItem", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}