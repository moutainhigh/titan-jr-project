package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanRefund;

public interface TitanRefundDao {
	
	int insert(TitanRefund entity) throws DaoException;
	
}
