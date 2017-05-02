package com.fangcang.titanjr.dao;


import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrderException;
import com.fangcang.titanjr.entity.parameter.TitanOrderExceptionParam;

/**
 * accountCode 编码
 * @author luoqinglong
 * @2016年5月12日
 */
public interface TitanOrderExceptionDao {
	/**
	 * 分页查询
	 * @param condition
	 * @param paginationSupport
	 * @return
	 * @throws DaoException
	 */
	PaginationSupport<TitanOrderException> selectForPage(TitanOrderExceptionParam condition, PaginationSupport<TitanOrderException> paginationSupport);
	
	/**
	 * 插入异常单
	 * @param titanOrderException
	 * @return
	 * @author fangdaikang
	 */
	int insertTitanOrderException(TitanOrderException titanOrderException) throws DaoException;
	/**
	 * 更新
	 * @param titanOrderException
	 * @return
	 * @throws DaoException
	 */
	int updateTitanOrderException(TitanOrderException titanOrderException) throws DaoException;
}
