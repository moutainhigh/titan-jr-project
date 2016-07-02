package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanAccountItem;
import com.fangcang.titanjr.entity.parameter.TitanAccountItemParam;

public interface TitanAccountItemDao {
	boolean selectForPage(TitanAccountItemParam condition, PaginationSupport<TitanAccountItem> paginationSupport) throws DaoException;
	int insert(TitanAccountItem entity) throws DaoException;
}