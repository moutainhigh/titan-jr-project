package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOrgCardMapDao;
import com.fangcang.titanjr.entity.TitanOrgCardMap;
import com.fangcang.titanjr.entity.parameter.TitanOrgCardMapParam;

public class TitanOrgCardMapDaoImpl extends GenericDAOMyBatisImpl implements TitanOrgCardMapDao {

	@Override
	public int insert(TitanOrgCardMap entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOrgCardMapDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanOrgCardMap entity) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TitanOrgCardMap> selectList(TitanOrgCardMapParam titanOrgCardMapParam) throws DaoException {
		try {
			if(titanOrgCardMapParam.getIsactive()==null){
				titanOrgCardMapParam.setIsactive(1);//有效
			}
			return super.selectList("com.fangcang.titanjr.dao.TitanOrgCardMapDao.queryList", titanOrgCardMapParam);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int delete(TitanOrgCardMapParam param) throws DaoException {
		return super.delete("com.fangcang.titanjr.dao.TitanOrgCardMapDao.deleteByOrgcode", param);
	}
	
}
