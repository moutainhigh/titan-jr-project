package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.request.TitanRoleQueryRequest;
import com.fangcang.titanjr.entity.TitanRole;
import com.fangcang.titanjr.entity.parameter.TitanRoleParam;

import java.util.List;

public interface TitanRoleDao {
	
	List<TitanRole> queryRoleList(TitanRoleParam titanRoleParam) throws DaoException;
	
    int insert(TitanRole entity) throws DaoException;

    int batchSaveRoles(List<TitanRole> roleList) throws DaoException;

    List<TitanRole> queryTitanRoles(TitanRoleQueryRequest titanRoleQueryRequest);
    
	public List<TitanRole> queryTitanRolesByRoleId(TitanRoleQueryRequest titanRoleQueryRequest);

}