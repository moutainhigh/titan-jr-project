package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanSynOrgInfoDao;
import com.fangcang.titanjr.entity.TitanSynOrgInfo;
import com.fangcang.titanjr.entity.parameter.TitanSynOrgInfoParam;

public class TitanSynOrgInfoDaoImpl extends GenericDAOMyBatisImpl implements TitanSynOrgInfoDao{

	@Override
	public PaginationSupport<TitanSynOrgInfo> selectForPage(
			TitanSynOrgInfoParam titanSynOrgInfoParam,
			PaginationSupport<TitanSynOrgInfo> paginationSupport)
			throws DaoException {
		try {
			paginationSupport = super.selectForPage("com.fangcang.titanjr.dao.TitanSynOrgInfoDao.queryList", titanSynOrgInfoParam, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return paginationSupport;
	}
	
	@Override
	public int insert(TitanSynOrgInfo entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanSynOrgInfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int deleteById(List<Integer> ids) {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanSynOrgInfoDao.deleteById", ids);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
}
