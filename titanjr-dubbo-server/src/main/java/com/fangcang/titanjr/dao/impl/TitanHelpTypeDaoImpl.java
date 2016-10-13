package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanHelpTypeDao;
import com.fangcang.titanjr.dto.bean.TitanHelpTypeDTO;
import com.fangcang.titanjr.entity.TitanHelpType;
import com.fangcang.titanjr.entity.parameter.TitanHelpTypeParam;

public class TitanHelpTypeDaoImpl extends GenericDAOMyBatisImpl implements TitanHelpTypeDao {

	
	@Override
	public PaginationSupport<TitanHelpTypeDTO> selectForPage(
			TitanHelpTypeParam condition,
			PaginationSupport<TitanHelpTypeDTO> paginationSupport)
			throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanHelpTypeDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return paginationSupport;
	}

	@Override
	public int insert(TitanHelpType entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanHelpTypeDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanHelpType entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanHelpTypeDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
