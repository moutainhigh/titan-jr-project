package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.titanjr.dao.TitanOrgidDao;
import com.fangcang.titanjr.entity.TitanOrgid;

public class TitanOrgidDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgidDao {

	@Override
	public Long getNextOrgId() {
		TitanOrgid entity = new TitanOrgid();
		super.insertEntity("com.fangcang.titanjr.dao.TitanOrgidDao.getNextOrgId", entity);
		return entity.getId();
	}
	
}
