package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanRateConfig;
import com.fangcang.titanjr.entity.parameter.TitanRateConfigParam;

import java.util.List;

public interface TitanRateConfigDao {

	int batchSaveRateConfigs(List<TitanRateConfig> titanRateConfigs) throws DaoException;

	int update(TitanRateConfig entity) throws DaoException;
}