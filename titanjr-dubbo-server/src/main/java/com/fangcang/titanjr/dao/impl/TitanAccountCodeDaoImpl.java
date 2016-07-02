package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.titanjr.dao.TitanAccountCodeDao;
import com.fangcang.titanjr.entity.TitanAccountCode;

public class TitanAccountCodeDaoImpl extends GenericDAOMyBatisImpl implements TitanAccountCodeDao {

	@Override
	public Long getNextAccountCodeId() {
		TitanAccountCode entity = new TitanAccountCode();
		 super.insertEntity("com.fangcang.titanjr.dao.TitanAccountCodeDao.getNextAccountCodeId", entity);
		return entity.getId();
	}

}
