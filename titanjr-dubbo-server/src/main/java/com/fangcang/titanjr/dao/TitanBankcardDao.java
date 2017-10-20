package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.parameter.TitanBankcardParam;

public interface TitanBankcardDao {
	boolean selectForPage(TitanBankcardParam condition, PaginationSupport<TitanBankcard> paginationSupport) throws DaoException;
	int insert(TitanBankcard entity) throws DaoException;
	int update(TitanBankcard entity) throws DaoException;
	/**
	 * 更新
	 * @param newUserId
	 * @param oldUserId
	 * @throws DaoException
	 */
	void updateUserId(String newUserId, String oldUserId) throws DaoException;
	
	int delete(TitanBankcard entity) throws DaoException;
	TitanBankcard selectEntity(TitanBankcardParam param) throws DaoException;
	int intsertBatch(List<TitanBankcard> list) throws DaoException;
}