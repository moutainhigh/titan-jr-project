package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.entity.TitanFundFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanFundFreezereqParam;

public interface TitanFundFreezereqDao {
	boolean selectForPage(TitanFundFreezereqParam condition, PaginationSupport<TitanFundFreezereq> paginationSupport) throws DaoException;
	int insert(TitanFundFreezereq entity) throws DaoException;
	int update(TitanFundFreezereq entity) throws DaoException;
	
	List<FundFreezeDTO> queryFundFreezeDTO(UnFreezeRequest unFreezeRequest) throws DaoException;
	
	List<FundFreezeDTO> queryFundFreezeDTOByOrderNo(String orderno) throws DaoException;
}