package com.fangcang.titanjr.dao.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanUserBindInfoDao;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.entity.TitanUserBindInfo;
import com.fangcang.titanjr.entity.parameter.TitanUserBindInfoParam;

public class TitanUserBindInfoDaoImpl extends GenericDAOMyBatisImpl implements TitanUserBindInfoDao {

	
	@Override
	public PaginationSupport<TitanUserBindInfo> selectForPage(TitanUserBindInfoParam condition,PaginationSupport<TitanUserBindInfo> paginationSupport)
			throws DaoException {
		try {
			return super.selectForPage("com.fangcang.titanjr.dao.TitanUserBindInfoDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int insert(TitanUserBindInfo entity) throws DaoException {
		try {
			return	super.insertEntity("com.fangcang.titanjr.dao.TitanUserBindInfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanUserBindInfo entity) throws DaoException{
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanUserBindInfoDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	@Override
	public boolean delete(TitanUserBindInfoParam param) throws DaoException {
		try {
			 super.delete("com.fangcang.titanjr.dao.TitanUserBindInfoDao.deleteEntity", param);
			 return true;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int updateIsactiveBatch(Integer isactive,Integer coopType,String merchantcode,String modifior,Date modifytime) throws DaoException{
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("isactive", isactive);
			param.put("cooptype", coopType);
			param.put("merchantcode", merchantcode);
			param.put("modifior", modifior);
			param.put("modifytime", modifytime);
			return super.updateEntity("com.fangcang.titanjr.dao.TitanUserBindInfoDao.updateIsactiveBatch", param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<TitanUserBindInfo> selectUserBindInfoByFcuserid(TitanUserBindInfo titanUserBindInfo)
			throws DaoException {
		try {
			if(titanUserBindInfo.getTfsuserid()!=null || titanUserBindInfo.getFcuserid()!=null){
				return getSqlSession().selectList("com.fangcang.titanjr.dao.TitanUserBindInfoDao.selectUserBindInfoByFcuserid", titanUserBindInfo);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return null;
	}
	
	@Override
	public TitanUserBindInfo queryAdminUserBindInfo(UserInfoQueryRequest userInfoQueryRequest) {
		try {
			return (TitanUserBindInfo)super.selectOne("com.fangcang.titanjr.dao.TitanUserBindInfoDao.queryAdminUserBindInfo", userInfoQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}