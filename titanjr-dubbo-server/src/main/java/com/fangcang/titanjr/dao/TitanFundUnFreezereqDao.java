package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;
import com.fangcang.titanjr.entity.parameter.TitanUnFundFreezereqParam;

public interface TitanFundUnFreezereqDao {
	boolean selectForPage(TitanUnFundFreezereqParam condition, PaginationSupport<TitanFundUnFreezereq> paginationSupport) throws DaoException;
	int insert(TitanFundUnFreezereq entity) throws DaoException;
	int update(TitanFundUnFreezereq entity) throws DaoException;
}