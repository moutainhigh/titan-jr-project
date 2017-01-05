package com.fangcang.titanjr.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.PlatformOrderOprDao;
import com.fangcang.titanjr.entity.PlatformOrderStat;
import com.fangcang.titanjr.entity.parameter.PlatformOrderStatConditons;

public class PlatformOrderOprDaoImpl extends GenericDAOMyBatisImpl implements
		PlatformOrderOprDao {

	private static final Log log = LogFactory
			.getLog(PlatformOrderOprDaoImpl.class);

	@Override
	public PlatformOrderStat queryPlatformStatInfo(
			PlatformOrderStatConditons conditons) {

		try {
			return (PlatformOrderStat) super
					.selectOne(
							"com.fangcang.titanjr.dao.PlatformOrderOprDao.queryOrderStatInfo",
							conditons);
		} catch (Exception e) {
			log.error("queryPlatformStatInfo Error", e);
			throw new DaoException(e);
		}
	}

}
