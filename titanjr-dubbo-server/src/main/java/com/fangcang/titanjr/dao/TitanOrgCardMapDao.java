package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgCardMap;
import com.fangcang.titanjr.entity.parameter.TitanOrgCardMapParam;

public interface TitanOrgCardMapDao {
	int insert(TitanOrgCardMap entity) throws DaoException;
	
	int update(TitanOrgCardMap entity) throws DaoException;
	
	List<TitanOrgCardMap> selectList(TitanOrgCardMapParam param) throws DaoException;
	
	int delete(TitanOrgCardMapParam param) throws DaoException;
	
}
