package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.entity.TitanCommonPayMethod;
import com.fangcang.titanjr.entity.parameter.TitanCommonPayMethodParam;

public interface TitanCommonPayMethodDao {
	
	boolean selectForPage(TitanCommonPayMethodParam condition, PaginationSupport<TitanCommonPayMethod> paginationSupport) throws DaoException;
	
	int insert(TitanCommonPayMethod entity) throws DaoException;
	
	int update(TitanCommonPayMethod entity) throws DaoException;
	
	List<CommonPayMethodDTO> queryCommomPayMethod(CashierDeskQueryRequest cashierDeskQueryRequest) throws DaoException;
}