package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;

/**
 * 泰坦码
 * @author luoqinglong
 * @2016年5月6日
 */
public interface TitanCodeDao {
	/**
	 * 生成下一个id
	 * @return
	 */
	Long getNextTitanCode() throws DaoException;;
}