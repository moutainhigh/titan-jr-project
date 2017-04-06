package com.fangcang.titanjr.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanVirtualOrgDao;
import com.fangcang.titanjr.entity.TitanVirtualOrg;
import com.fangcang.titanjr.entity.VirtualOrgRelation;

public class TitanVirtualOrgDaoImpl extends GenericDAOMyBatisImpl implements
		TitanVirtualOrgDao {

	private static final Log log = LogFactory
			.getLog(TitanVirtualOrgDaoImpl.class);

	public List<TitanVirtualOrg> queryVirtualOrgInfos(TitanVirtualOrg orgCode) {
		try {
			return super
					.selectList(
							"com.fangcang.titanjr.dao.TitanVirtualOrgDao.queryVirtualOrgInfos",
							orgCode);
		} catch (Exception e) {
			log.error("queryVirtualOrgInfos Error", e);
			throw new DaoException(e);
		}
	}

	public void addVirtualOrg(TitanVirtualOrg org) {
		try {
			super.insertEntity(
					"com.fangcang.titanjr.dao.TitanVirtualOrgDao.insertVirtualOrg",
					org);
		} catch (Exception e) {
			log.error("addVirtualOrg Error", e);
			throw new DaoException(e);
		}
	}

	public List<VirtualOrgRelation> queryVirtualOrgRelationInfos(
			VirtualOrgRelation orgRelation) {
		try {
			return super
					.selectList(
							"com.fangcang.titanjr.dao.TitanVirtualOrgDao.queryVirtualOrgRelationInfos",
							orgRelation);
		} catch (Exception e) {
			log.error("queryVirtualOrgRelationInfos Error", e);
			throw new DaoException(e);
		}
	}

	public void addVirtualOrgRelation(VirtualOrgRelation orgRelation) {
		try {
			super.insertEntity(
					"com.fangcang.titanjr.dao.TitanVirtualOrgDao.insertVirtualOrgRelation",
					orgRelation);
		} catch (Exception e) {
			log.error("addVirtualOrgRelation Error", e);
			throw new DaoException(e);
		}
	}

}
