package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RepairTransferRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.TransOrderResponse;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.entity.parameter.TitanTransOrderParam;

public interface TitanTransOrderDao {
	boolean selectForPage(TitanTransOrderParam condition, PaginationSupport<TitanTransOrder> paginationSupport) throws DaoException;
	public boolean selectOrderForPage(TitanTransOrderParam condition,PaginationSupport<TitanTransOrder> paginationSupport)throws DaoException;
	
	int insert(TitanTransOrder entity) throws DaoException;
	int update(TitanTransOrder entity) throws DaoException;
	
	List<TransOrderDTO> selectTitanTransOrder(TransOrderRequest transOrderRequest)throws DaoException;
	
	List<RepairTransferDTO> queryTitanTransOrderByStatus(RepairTransferRequest repairTransferRequest);
	
	int updateTitanTransOrderByTransId(TitanTransOrder entity)throws DaoException;
	
	List<String> confirmOrderStatus(String orderNo);
}