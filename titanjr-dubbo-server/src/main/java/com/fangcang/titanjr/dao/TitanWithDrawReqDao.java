package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;

public interface TitanWithDrawReqDao {
	boolean selectForPage(TitanWithDrawReqParam condition, PaginationSupport<TitanWithDrawReq> paginationSupport) throws DaoException;
	int insert(TitanWithDrawReq entity) throws DaoException;
	int update(TitanWithDrawReq entity) throws DaoException;
}