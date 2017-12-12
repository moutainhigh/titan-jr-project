package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanAccountDetail;

public interface TitanAccountDetailDao {
	int insert(TitanAccountDetail entity) throws DaoException;
}
