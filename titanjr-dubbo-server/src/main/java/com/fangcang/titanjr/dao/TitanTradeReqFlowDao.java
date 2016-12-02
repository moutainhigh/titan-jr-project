package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanTradeReqFlow;
import com.fangcang.titanjr.entity.parameter.TitanTradeReqFlowParam;

public interface TitanTradeReqFlowDao {
	boolean selectForPage(TitanTradeReqFlowParam condition, PaginationSupport<TitanTradeReqFlow> paginationSupport) throws DaoException;
	int insert(TitanTradeReqFlow entity) throws DaoException;
	int update(TitanTradeReqFlow entity) throws DaoException;
}