package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCommonPayMethodDao;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.entity.TitanCommonPayMethod;
import com.fangcang.titanjr.entity.parameter.TitanCommonPayMethodParam;

public class TitanCommonPayMethodDaoImpl extends GenericDAOMyBatisImpl implements TitanCommonPayMethodDao{
	@Override
	public boolean selectForPage(TitanCommonPayMethodParam condition,
			PaginationSupport<TitanCommonPayMethod> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanCommonPayMethodDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanCommonPayMethod entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanCommonPayMethodDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanCommonPayMethod entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanCommonPayMethodDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CommonPayMethodDTO> queryCommomPayMethod(
			CashierDeskQueryRequest cashierDeskQueryRequest)
			throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanCommonPayMethodDao.selectCommonPayMethod", cashierDeskQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}