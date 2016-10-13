package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.HelpWordDTO;
import com.fangcang.titanjr.dto.bean.TitanHelpDTO;
import com.fangcang.titanjr.entity.TitanHelp;
import com.fangcang.titanjr.entity.parameter.TitanHelpParam;

public interface TitanHelpDao {
	
	PaginationSupport<TitanHelpDTO> selectForPage(TitanHelpParam condition, PaginationSupport<TitanHelpDTO> paginationSupport) throws DaoException;
	/**
	 * 标题和内容都搜索
	 * @param condition
	 * @param paginationSupport
	 * @return
	 * @throws DaoException
	 */
	PaginationSupport<HelpWordDTO> selectForPageSearch(TitanHelpParam condition, PaginationSupport<HelpWordDTO> paginationSupport) throws DaoException;
	
	int insert(TitanHelp entity) throws DaoException;
	
	int update(TitanHelp entity) throws DaoException;
}
