package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOperateLog;

/**
 * 泰坦码
 * @author luoqinglong
 * @2016年5月6日
 */
public interface TitanOperateLogDao {
	int insert(TitanOperateLog entity) throws DaoException;
}