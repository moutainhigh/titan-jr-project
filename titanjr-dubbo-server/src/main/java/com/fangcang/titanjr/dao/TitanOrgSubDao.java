package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgSub;
import com.fangcang.titanjr.entity.parameter.TitanOrgSubParam;


/**
 * 子机构
 * @author luoqinglong
 *
 */
public interface TitanOrgSubDao {
	PaginationSupport<TitanOrgSub> selectForPage(TitanOrgSubParam condition, PaginationSupport<TitanOrgSub> paginationSupport) throws DaoException;
	
	int insert(TitanOrgSub entity) throws DaoException;
	
	TitanOrgSub getOneByOrgCode(String orgCode) throws DaoException;
	
	TitanOrgSub selectOne(TitanOrgSubParam condition) throws DaoException;
	
	int update(TitanOrgSubParam condition) throws DaoException;
}
