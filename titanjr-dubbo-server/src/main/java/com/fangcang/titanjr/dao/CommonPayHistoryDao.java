package com.fangcang.titanjr.dao;

import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.gateway.CommonPayHistoryDTO;

public interface CommonPayHistoryDao {
	
	/**
	 * 分页
	 * @author Jerry
	 * @date 2018年1月30日 上午11:13:02
	 */
	boolean selectForPage(Map<String, String> condition, PaginationSupport<CommonPayHistoryDTO> paginationSupport) throws DaoException;
	
	int insert(CommonPayHistoryDTO entity) throws DaoException;
	
	int update(CommonPayHistoryDTO entity) throws DaoException;
	
	/**
	 * 查询列表
	 * @author Jerry
	 * @date 2018年1月30日 上午11:12:27
	 */
	List<CommonPayHistoryDTO> selectCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO) throws DaoException;
	
	/**
	 * 单个实体
	 * @author Jerry
	 * @date 2018年1月30日 上午11:12:45
	 */
	CommonPayHistoryDTO getCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO) throws DaoException;
	
	int delCommonPayHistory(CommonPayHistoryDTO commonPayHistoryDTO) throws DaoException;
}