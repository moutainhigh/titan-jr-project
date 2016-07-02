package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanUserRoleDao;
import com.fangcang.titanjr.entity.TitanUserRole;
import com.fangcang.titanjr.entity.parameter.TitanUserRoleParam;

import java.util.List;

public class TitanUserRoleDaoImpl extends GenericDAOMyBatisImpl implements TitanUserRoleDao{
	@Override
	public boolean selectForPage(TitanUserRoleParam condition,PaginationSupport<TitanUserRole> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanUserRoleDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return true;
	}

	@Override
	public int insert(TitanUserRole entity) throws DaoException {
		try {
			return	super.insertEntity("com.fangcang.titanjr.dao.TitanUserRoleDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int batchSaveUserRoles(List<TitanUserRole> userRoleList) throws DaoException {
		try {
			return	super.insertEntity("com.fangcang.titanjr.dao.TitanUserRoleDao.batchSaveUserRoles", userRoleList);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanUserRole entity) {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanUserRoleDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int deleteUserRole(TitanUserRoleParam condition)
			throws DaoException {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanUserRoleDao.deleteUserRole", condition);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<TitanUserRole> selectUserRolesByuserid(String tfsuserid)
			throws DaoException {
		return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanUserRoleDao.selectUserRoles",tfsuserid);
	}
}