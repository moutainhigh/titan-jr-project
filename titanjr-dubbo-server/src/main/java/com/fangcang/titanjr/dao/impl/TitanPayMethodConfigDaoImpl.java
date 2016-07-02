package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanPayMethodConfigDao;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.entity.TitanPayMethodConfig;
import com.fangcang.titanjr.rs.entity.TitanPayMethod;

public class TitanPayMethodConfigDaoImpl extends GenericDAOMyBatisImpl implements TitanPayMethodConfigDao {


	@Override
	public List<TitanPayMethodConfig> queryTitanPayMethod(
			PayMethodConfigRequest payMethodConfigRequest) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanPayMethodConfigDao.queryTitanPayMethodConfig", payMethodConfigRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
