package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgMapInfo;
import com.fangcang.titanjr.entity.parameter.TitanOrgMapInfoParam;

/**
 * 子机构
 * @author luoqinglong
 *
 */
public interface TitanOrgMapInfoDao {
	
	int insert(TitanOrgMapInfo entity) throws DaoException;
	
	List<TitanOrgMapInfo> queryList(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException;
}
