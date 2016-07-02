package com.fangcang.titanjr.dao;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgImage;
import com.fangcang.titanjr.entity.parameter.TitanOrgImageParam;

public interface TitanOrgImageDao {

	PaginationSupport<TitanOrgImage> selectForPage(TitanOrgImageParam condition, PaginationSupport<TitanOrgImage> paginationSupport) throws DaoException;
	int insert(TitanOrgImage entity) throws DaoException;
	int update(TitanOrgImage entity) throws DaoException;
}