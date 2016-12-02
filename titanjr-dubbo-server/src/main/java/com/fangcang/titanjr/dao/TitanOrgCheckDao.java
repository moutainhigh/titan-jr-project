package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgCheck;
import com.fangcang.titanjr.entity.parameter.TitanOrgCheckParam;

public interface TitanOrgCheckDao {
	boolean selectForPage(TitanOrgCheckParam condition, PaginationSupport<TitanOrgCheck> paginationSupport) throws DaoException;
	int insert(TitanOrgCheck entity) throws DaoException;
	int update(TitanOrgCheck entity) throws DaoException;
}