package com.fangcang.titanjr.dao;

import java.util.Date;
import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanUserBindInfo;
import com.fangcang.titanjr.entity.parameter.TitanUserBindInfoParam;

public interface TitanUserBindInfoDao {
	PaginationSupport<TitanUserBindInfo> selectForPage(TitanUserBindInfoParam condition, PaginationSupport<TitanUserBindInfo> paginationSupport) throws DaoException;
	int insert(TitanUserBindInfo entity) throws DaoException;
	int update(TitanUserBindInfo entity) throws DaoException;
	/**
	 * 批量更新绑定状态
	 * @param isactive
	 * @param merchantcode
	 * @return
	 * @throws DaoException
	 */
	int updateIsactiveBatch(Integer isactive,String merchantcode,String modifior,Date modifytime) throws DaoException;
	List<TitanUserBindInfo> selectUserBindInfoByFcuserid(TitanUserBindInfo titanUserBindInfo)throws DaoException;
}
