package com.fangcang.titanjr.dao;

import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.gateway.CommonPayHistoryDTO;

public interface CommonPayHistoryDao {
	
	boolean selectForPage(Map<String, String> condition, PaginationSupport<CommonPayHistoryDTO> paginationSupport) throws DaoException;
	
	int insert(CommonPayHistoryDTO entity) throws DaoException;
	
	int update(CommonPayHistoryDTO entity) throws DaoException;
	
	List<CommonPayHistoryDTO> selectCommonPayHistory(CommonPayHistoryDTO quickCardHistoryDTO) throws DaoException;
}