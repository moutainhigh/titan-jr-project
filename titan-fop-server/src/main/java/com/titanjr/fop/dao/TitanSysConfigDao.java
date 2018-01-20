package com.titanjr.fop.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.titanjr.fop.entity.TitanSysConfig;

public interface TitanSysConfigDao {
	/**
	 * 查询配置表
	 * @param sysConfig
	 * @return
	 * @throws DaoException
     */
	List<TitanSysConfig> querySysConfigList(TitanSysConfig sysConfig) throws DaoException;
	
}
