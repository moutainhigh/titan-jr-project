package com.fangcang.titanjr.dao;


import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.TitanHelpTypeDTO;
import com.fangcang.titanjr.entity.TitanHelpType;
import com.fangcang.titanjr.entity.parameter.TitanHelpTypeParam;

/**
 * 帮助问题
 * @author luoqinglong
 * @2016年9月22日
 */
public interface TitanHelpTypeDao {
	
	int insert(TitanHelpType entity) throws DaoException;
	
	int update(TitanHelpType entity) throws DaoException;
	
	PaginationSupport<TitanHelpTypeDTO> selectForPage(TitanHelpTypeParam condition,PaginationSupport<TitanHelpTypeDTO> paginationSupport)throws DaoException;
}
