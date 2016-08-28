package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.titanjr.dao.TitanSysconfigDao;
import com.fangcang.titanjr.entity.TitanSysconfig;
import com.fangcang.titanjr.entity.parameter.TitanSysconfigParam;
import com.sun.tools.javac.util.List;

public class TitanSysconfigDaoImpl  extends GenericDAOMyBatisImpl implements TitanSysconfigDao {

	@Override
	public List<TitanSysconfig> query(TitanSysconfigParam titanSysconfigParam) {
		
		super.selectList("", titanSysconfigParam);
		return null;
	}

}
