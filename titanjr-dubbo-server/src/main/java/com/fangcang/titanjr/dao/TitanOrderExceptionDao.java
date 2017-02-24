package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrderException;

/**
 * accountCode 编码
 * @author luoqinglong
 * @2016年5月12日
 */
public interface TitanOrderExceptionDao {
	
	/**
	 * 查询异常单
	 * @param titanOrderException
	 * @return
	 */
	List<TitanOrderException> selectTitanOrderException(TitanOrderException titanOrderException)throws DaoException ;
		
	
	/**
	 * 插入异常单
	 * @param titanOrderException
	 * @return
	 * @author fangdaikang
	 */
	int insertTitanOrderException(TitanOrderException titanOrderException) throws DaoException;
}
