package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanCashierDeskItem;


public interface TitanCashierDeskItemDao {

	int saveCashierDeskItem(TitanCashierDeskItem cashierDeskItem) throws DaoException;

	int updateCashierDeskItem(TitanCashierDeskItem entity) throws DaoException;
}