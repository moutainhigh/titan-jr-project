package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanRateChangeLog;
import com.fangcang.titanjr.entity.parameter.TitanRateChangeLogParam;

public interface TitanRateChangeLogDao {
	boolean selectForPage(TitanRateChangeLogParam condition, PaginationSupport<TitanRateChangeLog> paginationSupport) throws DaoException;
	int insert(TitanRateChangeLog entity) throws DaoException;
	int update(TitanRateChangeLog entity) throws DaoException;
}