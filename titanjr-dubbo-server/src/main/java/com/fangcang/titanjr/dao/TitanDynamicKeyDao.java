package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanDynamicKey;
import com.fangcang.titanjr.entity.parameter.TitanDynamicKeyParam;

public interface TitanDynamicKeyDao {
	boolean selectForPage(TitanDynamicKeyParam condition, PaginationSupport<TitanDynamicKey> paginationSupport) throws DaoException;
	int insert(TitanDynamicKey entity) throws DaoException;
	int update(TitanDynamicKey entity) throws DaoException;
	
	int delete(TitanDynamicKey entity) throws DaoException;
	
	String selectKeyByPayOrderNo(TitanDynamicKey entity) throws DaoException;
}