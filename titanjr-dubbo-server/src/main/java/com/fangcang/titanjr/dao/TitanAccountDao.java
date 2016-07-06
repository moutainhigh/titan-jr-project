package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanAccount;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;

public interface TitanAccountDao {
	PaginationSupport<TitanAccount> selectForPage(TitanAccountParam condition, PaginationSupport<TitanAccount> paginationSupport) throws DaoException;
	int insert(TitanAccount entity) throws DaoException;
	int update(TitanAccount entity) throws DaoException;
}
