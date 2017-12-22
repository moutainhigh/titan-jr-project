package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanCheckCode;
import com.fangcang.titanjr.entity.parameter.TitanCheckCodeParam;
/**
 * 验证码
 * @author luoqinglong
 * @2016年7月21日
 */
public interface TitanCheckCodeDao {
	PaginationSupport<TitanCheckCode> selectForPage(TitanCheckCodeParam condition, PaginationSupport<TitanCheckCode> paginationSupport) throws DaoException;
	int insert(TitanCheckCode entity) throws DaoException;
	int update(TitanCheckCode entity) throws DaoException;
	/**
	 * 修改状态
	 * @param condition
	 * @return
	 * @throws DaoException
	 */
	int disableIsactive(TitanCheckCodeParam condition)throws DaoException;
}