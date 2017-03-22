package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanCoopDao;
import com.fangcang.titanjr.dto.bean.CoopDTO;
import com.fangcang.titanjr.entity.parameter.TitanCoopParam;

public class TitanCoopDaoImpl extends GenericDAOMyBatisImpl implements TitanCoopDao {

	@Override
	public CoopDTO getEntity(TitanCoopParam param) throws DaoException {
		try {
			return (CoopDTO)selectOne("com.fangcang.titanjr.dao.TitanCoopDao.getEntity", param);
			
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
