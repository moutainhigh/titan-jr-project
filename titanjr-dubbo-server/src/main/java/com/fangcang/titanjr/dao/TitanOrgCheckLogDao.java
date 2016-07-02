package com.fangcang.titanjr.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanOrgCheckLog;

public interface TitanOrgCheckLogDao {
	int insert(TitanOrgCheckLog entity) throws DaoException;
}