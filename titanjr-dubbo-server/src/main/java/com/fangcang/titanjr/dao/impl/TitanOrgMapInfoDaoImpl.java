package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgMapInfoDao;
import com.fangcang.titanjr.entity.TitanOrgMapInfo;
import com.fangcang.titanjr.entity.parameter.TitanOrgMapInfoParam;
import com.fangcang.util.StringUtil;

public class TitanOrgMapInfoDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgMapInfoDao {

	@Override
	public int insert(TitanOrgMapInfo entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgMapInfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	@Override
	public int update(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanOrgMapInfoDao.updateEntity", titanOrgMapInfoParam);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}


	@Override
	public int delete(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException {
		
		if(StringUtil.isValidString(titanOrgMapInfoParam.getOrgCode())||StringUtil.isValidString(titanOrgMapInfoParam.getOrgSubcode())||titanOrgMapInfoParam.getOrgMapid().intValue()>0){
			return super.delete("com.fangcang.titanjr.dao.TitanOrgMapInfoDao.delete", titanOrgMapInfoParam);
		}else{
			throw new DaoException("必填参数不能为空");
		}
		
	}


	@Override
	public List<TitanOrgMapInfo> queryList(TitanOrgMapInfoParam titanOrgMapInfoParam) throws DaoException {
		if(titanOrgMapInfoParam.getIsactive()==null){
			titanOrgMapInfoParam.setIsactive(1);//1-有效，0-无效
		}else if(titanOrgMapInfoParam.getIsactive()>99){//不加该条件
			titanOrgMapInfoParam.setIsactive(null);
		}
		
		return super.selectList("com.fangcang.titanjr.dao.TitanOrgMapInfoDao.queryList", titanOrgMapInfoParam);
	}

	@Override
	public TitanOrgMapInfo getOneTitanOrgMapInfo(String orgCode) throws DaoException {
		TitanOrgMapInfoParam titanOrgMapInfoParam = new TitanOrgMapInfoParam();
		titanOrgMapInfoParam.setOrgCode(orgCode);
		List<TitanOrgMapInfo> list = queryList(titanOrgMapInfoParam);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}else if(list.size()>1){
			throw new DaoException("机构关联关系错误，有效关联关系记录超过一条");
		}
		return null;
	}
	

}
