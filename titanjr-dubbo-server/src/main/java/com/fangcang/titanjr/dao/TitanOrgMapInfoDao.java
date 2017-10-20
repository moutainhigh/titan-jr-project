package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgMapInfo;
import com.fangcang.titanjr.entity.parameter.TitanOrgMapInfoParam;

/**
 * 子机构关联关系
 * @author luoqinglong
 *
 */
public interface TitanOrgMapInfoDao {
	
	int insert(TitanOrgMapInfo entity) throws DaoException;
	
	int update(TitanOrgMapInfoParam titanOrgMapInfoParam)throws DaoException;
	
	int delete(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException;
	/***
	 * 
	 * @param titanOrgMapInfoParam
	 * @return
	 * @throws DaoException
	 */
	List<TitanOrgMapInfo> queryList(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException;
	/**
	 * 查询一个有效的关联关系，如果存在多个，就报错
	 * @param orgCode 虚拟机构编码
	 * @return
	 * @throws DaoException
	 */
	TitanOrgMapInfo getOneTitanOrgMapInfo(String orgCode) throws DaoException;
	
	
	
}
