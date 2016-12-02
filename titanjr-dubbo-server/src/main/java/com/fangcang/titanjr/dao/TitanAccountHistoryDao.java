package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.entity.TitanAccountHistory;
import com.fangcang.titanjr.entity.parameter.TitanAccountHistoryParam;

public interface TitanAccountHistoryDao {
	boolean selectForPage(TitanAccountHistoryParam condition, PaginationSupport<TitanAccountHistory> paginationSupport) throws DaoException;
	int insert(TitanAccountHistory entity) throws DaoException;
	int update(TitanAccountHistory entity) throws DaoException;
	
	List<AccountHistoryDTO> queryAccountHistory(AccountHistoryDTO accountHistoryDTO) throws DaoException;
	
	int deleteAccountHistory(AccountHistoryDTO accountHistoryDTO) throws DaoException;
}