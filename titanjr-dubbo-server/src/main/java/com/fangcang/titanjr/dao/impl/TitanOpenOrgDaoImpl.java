package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanOpenOrgDao;
import com.fangcang.titanjr.dto.bean.TitanOpenOrgDTO;
import com.fangcang.titanjr.entity.TitanOpenOrg;

public class TitanOpenOrgDaoImpl extends GenericDAOMyBatisImpl implements TitanOpenOrgDao {

	@Override
	public int insert(TitanOpenOrg entity) throws DaoException {
		try{
			return super.insertEntity("com.fangcang.titanjr.dao.TitanOpenOrgDao.insertEntity", entity);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int delete(TitanOpenOrg entity) throws DaoException {
		try{
			return super.delete("com.fangcang.titanjr.dao.TitanOpenOrgDao.delete", entity);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<TitanOpenOrgDTO> selectList(TitanOpenOrg entity) throws DaoException {
		try{
			return super.selectList("com.fangcang.titanjr.dao.TitanOpenOrgDao.queryList", entity);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<String> selectMaxPrefix() throws DaoException {
		try{
			return super.selectList("com.fangcang.titanjr.dao.TitanOpenOrgDao.queryMaxPrifix");
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
