package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgSub;
import com.fangcang.titanjr.entity.parameter.TitanOrgSubParam;


/**
 * 子机构
 * @author luoqinglong
 *
 */
public interface TitanOrgSubDao {
	
	int insert(TitanOrgSub entity) throws DaoException;
	
	TitanOrgSub getOneByOrgCode(String orgCode) throws DaoException;
	
	TitanOrgSub selectOne(TitanOrgSubParam condition) throws DaoException;
	
	int update(TitanOrgSub entity) throws DaoException;
}
