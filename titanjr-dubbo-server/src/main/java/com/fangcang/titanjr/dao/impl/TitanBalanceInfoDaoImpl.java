package com.fangcang.titanjr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fangcang.corenut.dao.GenericDAO;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanBalanceInfoDao;
import com.fangcang.titanjr.entity.TitanBalanceInfo;
import com.fangcang.titanjr.entity.parameter.TitanBalanceInfoParam;

public class TitanBalanceInfoDaoImpl implements TitanBalanceInfoDao {

	@Resource
	private GenericDAO genericDAO ;
	
	@Override
	public boolean selectForPage(TitanBalanceInfoParam param,
			PaginationSupport<TitanBalanceInfo> paginationSupport) throws DaoException {
		
		try {
			  genericDAO.selectForPage("com.fangcang.titanjr.dao.TitanBalanceInfoDao.queryList", param, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return true;
	}

	@Override
	public List<TitanBalanceInfo> queryList(TitanBalanceInfoParam param) throws DaoException {

		try {
			 return genericDAO.selectList("com.fangcang.titanjr.dao.TitanBalanceInfoDao.queryList", param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int insert(TitanBalanceInfo entity) throws DaoException {
		try {
			return genericDAO.insertEntity("com.fangcang.titanjr.dao.TitanBalanceInfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanBalanceInfo entity) throws DaoException {
		try {
			return genericDAO.insertEntity("com.fangcang.titanjr.dao.TitanBalanceInfoDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
