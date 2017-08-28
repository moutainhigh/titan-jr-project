package com.fangcang.titanjr.dao.impl;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanOrgSubDao;
import com.fangcang.titanjr.entity.TitanOrgSub;
import com.fangcang.titanjr.entity.parameter.TitanOrgSubParam;

public class TitanOrgSubDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgSubDao {
	private final Logger LOG = LoggerFactory.getLogger(TitanOrgSubDaoImpl.class);
	
	@Override
	public int insert(TitanOrgSub entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgSubDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public TitanOrgSub getOneByOrgCode(String orgCode) throws DaoException {
		TitanOrgSubParam param = new TitanOrgSubParam();
		param.setOrgcode(orgCode);
		try {
			List<TitanOrgSub> list = super.selectList("com.fangcang.titanjr.dao.TitanOrgSubDao.queryList", param);
			if(CollectionUtils.isNotEmpty(list)&&list.size()==1){
				return list.get(0);
			}else if(list.size()>1){
				LOG.error("子机构存在多条记录，违背了机构唯一性原则，suborgCode:"+orgCode);
				return null;
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public TitanOrgSub selectOne(TitanOrgSubParam condition) throws DaoException {
		try {
			List<TitanOrgSub> list = super.selectList("com.fangcang.titanjr.dao.TitanOrgSubDao.queryList", condition);
			if(CollectionUtils.isNotEmpty(list)&&list.size()==1){
				return list.get(0);
			}else if(list.size()>1){
				LOG.error("子机构查询存在多条记录，违背了机构唯一性原则，查询条件TitanOrgSubParam："+Tools.gsonToString(condition));
				return null;
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrgSub entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgSubDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

}
