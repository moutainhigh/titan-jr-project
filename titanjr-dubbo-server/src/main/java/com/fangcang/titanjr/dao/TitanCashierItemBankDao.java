package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanCashierItemBank;

import java.util.List;

public interface TitanCashierItemBankDao {

	int batchSaveItemBanks(List<TitanCashierItemBank> itemBanks) throws DaoException;

	int update(TitanCashierItemBank entity) throws DaoException;
	
	List<TitanCashierItemBank>  queryCashierItemBankDTOByBankName(String bankname)throws DaoException;
}