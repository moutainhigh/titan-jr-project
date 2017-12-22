package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanBalanceInfo;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;
import com.fangcang.titanjr.entity.parameter.TitanBalanceInfoParam;

public interface TitanBalanceInfoDao {
	
	boolean selectForPage(TitanBalanceInfoParam condition, PaginationSupport<TitanBalanceInfo> paginationSupport) throws DaoException;
	
	List<TitanBalanceInfo> queryList(TitanBalanceInfoParam param) throws DaoException;
	
	int insert(TitanBalanceInfo entity) throws DaoException;
	
	int update(TitanBalanceInfo entity) throws DaoException;
}
