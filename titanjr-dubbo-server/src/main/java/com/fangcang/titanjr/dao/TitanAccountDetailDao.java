package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanAccountDetail;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;

public interface TitanAccountDetailDao {
	
	List<TitanAccountDetail> selectList(TitanAccountDetailParam param) throws DaoException;
	
	int insert(TitanAccountDetail entity) throws DaoException;
	
	
	
}
