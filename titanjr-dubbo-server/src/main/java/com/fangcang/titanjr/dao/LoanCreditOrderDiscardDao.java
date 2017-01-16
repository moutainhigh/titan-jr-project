package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.LoanCreditOrderDiscard;

public interface LoanCreditOrderDiscardDao {
	
	int insert(LoanCreditOrderDiscard entity) throws DaoException;
}
