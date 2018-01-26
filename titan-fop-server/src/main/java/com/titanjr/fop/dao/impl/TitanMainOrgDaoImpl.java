package com.titanjr.fop.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.common.util.Tools;
import com.titanjr.fop.dao.TitanMainOrgDao;
import com.titanjr.fop.dto.MainOrgDTO;
import com.titanjr.fop.entity.TitanMainOrg;

public class TitanMainOrgDaoImpl extends GenericDAOMyBatisImpl implements TitanMainOrgDao {

	@Override
	public int insert(TitanMainOrg entity) throws DaoException {
		try {
			return super.insertEntity("com.titanjr.fop.dao.TitanMainOrgDao.insertEntity", entity);
		} catch (Exception e) {
            logger.error("insert Error ,param:"+Tools.gsonToString(entity), e);
            throw new DaoException(e);
        }
	}

	@Override
	public List<TitanMainOrg> queryList(MainOrgDTO mainOrgDTO) throws DaoException {
		try {
            return super.selectList("com.titanjr.fop.dao.TitanMainOrgDao.queryList", mainOrgDTO);
        } catch (Exception e) {
            logger.error("queryList Error ,param:"+Tools.gsonToString(mainOrgDTO), e);
            throw new DaoException(e);
        }
	}

	@Override
	public int update(TitanMainOrg entity) throws DaoException {
		try {
			return super.updateEntity("com.titanjr.fop.dao.TitanMainOrgDao.updateEntity", entity);
		} catch (Exception e) {
            logger.error("update Error ,param:"+Tools.gsonToString(entity), e);
            throw new DaoException(e);
        }
	}

}
