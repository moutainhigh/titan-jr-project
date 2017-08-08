package com.fangcang.titanjr.dao;

import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.gateway.QuickCardHistoryDTO;

public interface QuickCardHistoryDao {
	
	boolean selectForPage(Map<String, String> condition, PaginationSupport<QuickCardHistoryDTO> paginationSupport) throws DaoException;
	
	int insert(QuickCardHistoryDTO entity) throws DaoException;
	
	int update(QuickCardHistoryDTO entity) throws DaoException;
	
	List<QuickCardHistoryDTO> selectQuickCardHistory(QuickCardHistoryDTO quickCardHistoryDTO) throws DaoException;
}