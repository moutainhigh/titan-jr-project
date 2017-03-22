package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanSynOrgInfo;
import com.fangcang.titanjr.entity.parameter.TitanSynOrgInfoParam;

/**
 * 同步机构信息
 * @author luoqinglong
 * @date   2017年2月28日
 */
public interface TitanSynOrgInfoDao {
	
	PaginationSupport<TitanSynOrgInfo> selectForPage(TitanSynOrgInfoParam titanSynOrgInfoParam,PaginationSupport<TitanSynOrgInfo> paginationSupport) throws DaoException;
	/**
	 * 根据id数组删除
	 * @param ids
	 */
	int deleteById(List<Integer> ids);
	
	int insert(TitanSynOrgInfo entity) throws DaoException;
}
