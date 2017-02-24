package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RepairTransferRequest;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;

public interface TitanTransferReqDao {
	boolean selectForPage(TitanTransferReqParam condition, PaginationSupport<TitanTransferReq> paginationSupport) throws DaoException;
	int insert(TitanTransferReq entity) throws DaoException;
	int update(TitanTransferReq entity) throws DaoException;
	
	int updateTitanTransferReqByTransorderid(TitanTransferReq titanTransferReq)throws DaoException;
	
	List<TitanTransferReq> queryTitanTransferReq(TitanTransferReqParam titanTransferReqParam);
	
	int delete(TitanTransferReq entity) throws DaoException ;
	
}