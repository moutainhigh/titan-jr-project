package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanAccountItemDao;
import com.fangcang.titanjr.entity.TitanAccountItem;
import com.fangcang.titanjr.entity.parameter.TitanAccountItemParam;

public class TitanAccountItemDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountItemDao{

	@Override
	public boolean selectForPage(TitanAccountItemParam condition,
			PaginationSupport<TitanAccountItem> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanAccountItemDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanAccountItem entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanAccountItemDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}