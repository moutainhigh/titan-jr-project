package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanCashierDeskItem;


public interface TitanCashierDeskItemDao {

	int saveCashierDeskItem(TitanCashierDeskItem cashierDeskItem) throws DaoException;

	int updateCashierDeskItem(TitanCashierDeskItem entity) throws DaoException;
	
	List<TitanCashierDeskItem> queryCashierDeskItems(TitanCashierDeskItem entity);
}