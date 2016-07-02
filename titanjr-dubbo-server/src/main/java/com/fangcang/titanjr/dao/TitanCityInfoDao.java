package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanCityInfo;
import com.fangcang.titanjr.entity.parameter.TitanCityInfoParam;

public interface TitanCityInfoDao {
	boolean selectForPage(TitanCityInfoParam condition, PaginationSupport<TitanCityInfo> paginationSupport) throws DaoException;
	int insert(TitanCityInfo entity) throws DaoException;
	int update(TitanCityInfo entity) throws DaoException;
	
	int insertBatch(List<TitanCityInfo> titanCityInfos);
	
	int deleteTitanCitys()throws DaoException;
}