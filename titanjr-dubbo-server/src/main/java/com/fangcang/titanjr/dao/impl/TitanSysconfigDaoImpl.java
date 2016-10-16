package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanSysconfigDao;
import com.fangcang.titanjr.entity.TitanSysconfig;
import com.fangcang.titanjr.entity.parameter.TitanSysconfigParam;

public class TitanSysconfigDaoImpl  extends GenericDAOMyBatisImpl implements TitanSysconfigDao {

	@Override
	public List<TitanSysconfig> query(TitanSysconfigParam titanSysconfigParam)
			throws DaoException {
		return super.selectList("com.fangcang.titanjr.dao.TitanSysconfigDao.query", titanSysconfigParam);
	}

	 

}
