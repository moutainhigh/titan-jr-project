package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrderPayreq;
import com.fangcang.titanjr.entity.parameter.TitanOrderPayreqParam;

public interface TitanOrderPayreqDao {
	boolean selectForPage(TitanOrderPayreqParam condition, PaginationSupport<TitanOrderPayreq> paginationSupport) throws DaoException;
	int insert(TitanOrderPayreq entity) throws DaoException;
	int update(TitanOrderPayreq entity) throws DaoException;
	
	int updateTitanOrderPayreqByOrderNo(TitanOrderPayreq entity) throws DaoException;
	
	
}