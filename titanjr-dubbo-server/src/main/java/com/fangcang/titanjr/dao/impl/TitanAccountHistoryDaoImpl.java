package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanAccountHistoryDao;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.entity.TitanAccountHistory;
import com.fangcang.titanjr.entity.parameter.TitanAccountHistoryParam;

public class TitanAccountHistoryDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountHistoryDao {

	@Override
	public boolean selectForPage(TitanAccountHistoryParam condition,
			PaginationSupport<TitanAccountHistory> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanAccountHistoryDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanAccountHistory entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanAccountHistoryDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanAccountHistory entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanAccountHistoryDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<AccountHistoryDTO> queryAccountHistory(
			AccountHistoryDTO accountHistoryDTO) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanAccountHistoryDao.queryAccountHistory", accountHistoryDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int deleteAccountHistory(AccountHistoryDTO accountHistoryDTO)
			throws DaoException {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanAccountHistoryDao.deleteAccountHistory", accountHistoryDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
