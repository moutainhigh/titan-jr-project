package com.fangcang.titanjr.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanBankcardDao;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.parameter.TitanBankcardParam;

public class TitanBankcardDaoImpl extends GenericDAOMyBatisImpl implements TitanBankcardDao{
	
	private static final Log log = LogFactory.getLog(TitanBankcardDaoImpl.class);

	@Override
	public boolean selectForPage(TitanBankcardParam condition,
			PaginationSupport<TitanBankcard> paginationSupport)
			throws DaoException {
		try {
			super.selectForPage("com.fangcang.titanjr.dao.TitanBankcardDao.queryList", condition, paginationSupport);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	@Override
	public int insert(TitanBankcard entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanBankcardDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int intsertBatch(List<TitanBankcard> list) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanBankcardDao.intsertBatch", list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(TitanBankcard entity) throws DaoException {
		try {
			return super.updateEntity("com.fangcang.titanjr.dao.TitanBankcardDao.updateEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int delete(TitanBankcard entity) throws DaoException {
		try {
			return super.delete("com.fangcang.titanjr.dao.TitanBankcardDao.deleteEntity", entity);
		} catch (Exception e) {
			log.error("删除本地对私卡绑定记录异常", e);
			throw new DaoException(e);
		}
	}
	
	
	@Override
	public TitanBankcard selectEntity(TitanBankcardParam param) throws DaoException {
		try {
			return (TitanBankcard)super.selectOne("com.fangcang.titanjr.dao.TitanBankcardDao.queryEntity", param);
		} catch (Exception e) {
			log.error("批量插入对私卡绑定记录异常", e);
			throw new DaoException(e);
		}
	}
	
}