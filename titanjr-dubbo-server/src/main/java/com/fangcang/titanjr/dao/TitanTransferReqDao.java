package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;

/***
 * 转账请求
 * @author luoqinglong
 * @date 2017年10月16日
 */
public interface TitanTransferReqDao {
	boolean selectForPage(TitanTransferReqParam condition, PaginationSupport<TitanTransferReq> paginationSupport) throws DaoException;
	int insert(TitanTransferReq entity) throws DaoException;
	int update(TitanTransferReq entity) throws DaoException;
	
	int updateTitanTransferReqByTransorderid(TitanTransferReq titanTransferReq)throws DaoException;
	
	List<TitanTransferReq> queryTitanTransferReq(TitanTransferReqParam titanTransferReqParam);

	List<TitanTransferReq> queryTransferByOrderNo(String orderNo);

	int delete(TitanTransferReq entity) throws DaoException ;
	
}