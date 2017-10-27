package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCashierDeskItemDao;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
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

	@Override
	public List<TitanCashierDeskItem> queryCashierDeskItems(TitanCashierDeskItem entity) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanCashierDeskItemDao.queryCashierDeskItems", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int delCashierdeskItemForTradingPlatform() {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanCashierDeskItemDao.delCashierdeskItemForTradingPlatform", null);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<CashierDeskDTO> queryTradingPlatformCashierdesk() {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanCashierDeskItemDao.queryTradingPlatformCashierdesk");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}