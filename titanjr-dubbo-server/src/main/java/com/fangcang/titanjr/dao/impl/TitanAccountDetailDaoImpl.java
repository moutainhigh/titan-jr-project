package com.fangcang.titanjr.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fangcang.corenut.dao.GenericDAO;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanAccountDetailDao;
import com.fangcang.titanjr.entity.TitanAccountDetail;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;

public class TitanAccountDetailDaoImpl implements TitanAccountDetailDao {
	
	@Resource(name="baseSqlMapClient")
	private GenericDAO genericDAO;

	@Override
	public int insert(TitanAccountDetail entity) throws DaoException {
		try {
			return genericDAO.insertEntity("com.fangcang.titanjr.dao.TitanAccountDetailDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanAccountDetail> selectList(TitanAccountDetailParam param) throws DaoException {
		
		try {
			return genericDAO.selectList("com.fangcang.titanjr.dao.TitanAccountDetailDao.queryList", param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
