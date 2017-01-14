package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanUserRole;
import com.fangcang.titanjr.entity.parameter.TitanUserRoleParam;

import java.util.List;

public interface TitanUserRoleDao {
	boolean selectForPage(TitanUserRoleParam condition, PaginationSupport<TitanUserRole> paginationSupport) throws DaoException;
	int insert(TitanUserRole entity) throws DaoException;

	int batchSaveUserRoles(List<TitanUserRole> userRoleList) throws DaoException;

	int update(TitanUserRole entity) throws DaoException;
	/**
	 * 删除用户权限
	 * @param condition
	 * @throws DaoException
	 */
	int deleteUserRole(TitanUserRoleParam condition) throws DaoException;;
	/**
	 * 查询某个用户的所有权限
	 * @param tfsuserid
	 * @return
	 * @throws DaoException
	 */
    List<TitanUserRole> selectUserRolesByuserid(String tfsuserid)throws DaoException;
    
}