package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCashierItemBankDao;
import com.fangcang.titanjr.entity.TitanCashierItemBank;

import java.util.List;

public class TitanCashierItemBankDaoImpl extends GenericDAOMyBatisImpl implements TitanCashierItemBankDao{

	@Override
	public int batchSaveItemBanks(List<TitanCashierItemBank> itemBanks) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanCashierItemBankDao.batchSaveItemBanks", itemBanks);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanCashierItemBank entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCashierItemBankDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanCashierItemBank> queryCashierItemBankDTOByBankName(
			String bankname) throws DaoException {
		try {
			return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanCashierItemBankDao.queryCashierItemBankDTOByBankName", bankname);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}