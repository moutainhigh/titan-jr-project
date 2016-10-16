package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.entity.TitanSysconfig;
import com.fangcang.titanjr.entity.parameter.TitanSysconfigParam;

public interface TitanSysconfigDao {
	
	List<TitanSysconfig> query(TitanSysconfigParam titanSysconfigParam) throws DaoException;
}
