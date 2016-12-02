package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanAccountOperLog;
import com.fangcang.titanjr.entity.parameter.TitanAccountOperLogParam;

public interface TitanAccountOperLogDao {
	boolean selectForPage(TitanAccountOperLogParam condition, PaginationSupport<TitanAccountOperLog> paginationSupport) throws DaoException;
	int insert(TitanAccountOperLog entity) throws DaoException;
	int update(TitanAccountOperLog entity) throws DaoException;
}