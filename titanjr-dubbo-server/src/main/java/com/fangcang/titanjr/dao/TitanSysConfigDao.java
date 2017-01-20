package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanSysConfig;

public interface TitanSysConfigDao {
	/**
	 * 查询配置表
	 * @param sysConfig
	 * @return
	 * @throws DaoException
     */
	List<TitanSysConfig> querySysConfigList(TitanSysConfig sysConfig) throws DaoException;

	/**
	 * 查询测试数据库连通性
	 * @throws DaoException
     */
	void accessQueryTest() throws DaoException;
}
