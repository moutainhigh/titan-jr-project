package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanRoleDao;
import com.fangcang.titanjr.dto.request.TitanRoleQueryRequest;
import com.fangcang.titanjr.entity.TitanRole;
import com.fangcang.titanjr.entity.parameter.TitanRoleParam;

import java.util.List;

public class TitanRoleDaoImpl extends GenericDAOMyBatisImpl implements TitanRoleDao{

	
	
	@Override
	public List<TitanRole> queryRoleList(TitanRoleParam titanRoleParam)
			throws DaoException {
		return super.selectList("com.fangcang.titanjr.dao.TitanRoleDao.queryList", titanRoleParam);
	}

	@Override
	public int insert(TitanRole entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanRoleDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int batchSaveRoles(List<TitanRole> roleList) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanRoleDao.batchSaveRole", roleList);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanRole> queryTitanRoles(TitanRoleQueryRequest titanRoleQueryRequest) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanRoleDao.queryTitanRoles", titanRoleQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<TitanRole> queryTitanRolesByRoleId(TitanRoleQueryRequest titanRoleQueryRequest) {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanRoleDao.queryTitanRolesById", titanRoleQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}