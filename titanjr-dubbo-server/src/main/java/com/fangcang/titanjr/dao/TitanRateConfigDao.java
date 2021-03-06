package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanRateConfig;
import com.fangcang.titanjr.entity.TitanRateRecord;
import com.fangcang.titanjr.entity.parameter.TitanRateConfigParam;

public interface TitanRateConfigDao {

	int batchSaveRateConfigs(List<TitanRateConfig> titanRateConfigs) throws DaoException;

	int update(TitanRateConfig entity) throws DaoException;
	

	List<TitanRateConfig> queryTitanRateConfigInfo(
			TitanRateConfigParam configParam) throws DaoException;
	
	
	int insertRateRecord(TitanRateRecord titanRateRecord);
	
	int deleteRateRecordByOrderNo(String orderNo);
	
	TitanRateRecord getRateRecordByOrderNo(String orderNo);
	
	List<String> queryAllUserId();
	/**
	 * 无某个费率的机构id（无用）
	 */
	List<String> queryUserIdNoRateConfig(Integer busType);
	
	public int truncateRateConfig();
}