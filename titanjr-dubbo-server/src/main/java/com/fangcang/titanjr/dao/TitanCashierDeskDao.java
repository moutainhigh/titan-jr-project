package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.entity.TitanCashierDesk;

import java.util.List;

public interface TitanCashierDeskDao {

	int saveCashierDesk(TitanCashierDesk entity) throws DaoException;

	int updateCashierDesk(TitanCashierDesk entity) throws DaoException;

	List<CashierDeskDTO> queryCashierDesk(CashierDeskQueryRequest cashierDeskQueryRequest) throws Exception;
	
}