package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanBankinfoDao;
import com.fangcang.titanjr.dto.bean.BankInfoDTO;
import com.fangcang.titanjr.dto.request.BankInfoQueryRequest;
import com.fangcang.titanjr.entity.TitanBankinfo;

public class TitanBankinfoDaoImpl extends GenericDAOMyBatisImpl implements TitanBankinfoDao{

	@Override
	public List<BankInfoDTO> queryBankInfoList(BankInfoQueryRequest bankInfoQueryRequest) throws DaoException {
		try {
			return super.selectList("com.fangcang.titanjr.dao.TitanBankinfoDao.queryBankInfoList", bankInfoQueryRequest);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int insert(TitanBankinfo entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanBankinfoDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanBankinfo entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanBankinfoDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int insertBatch(List<TitanBankinfo> titanBankinfo) {
		try{
			return  getSqlSession().insert("com.fangcang.titanjr.dao.TitanBankinfoDao.insertBatch", titanBankinfo);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public int delete() throws DaoException {
		try{
			return  getSqlSession().insert("com.fangcang.titanjr.dao.TitanBankinfoDao.deleteTitanBanks");
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}