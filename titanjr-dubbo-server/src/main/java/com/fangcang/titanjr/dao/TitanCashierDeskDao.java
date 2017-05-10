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
	
	List<CashierDeskDTO> queryNotAssociatedLoanCashierdesk() throws Exception;
	/**
	 * 对旧机构补充微信公众号收银台
	 * @return
	 * @throws DaoException
	 */
	List<String> queryNotExistWxPublic() throws DaoException;
}