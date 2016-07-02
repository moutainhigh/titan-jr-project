package com.fangcang.titanjr.rs.dao.impl;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.dao.RSInvokeConfigDao;
import com.fangcang.titanjr.rs.entity.RSInvokeConfig;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class RSInvokeConfigDaoImpl extends SqlSessionDaoSupport implements RSInvokeConfigDao {
    
    @Override
    public List<RSInvokeConfig> queryRSInvokeConfig() throws DaoException{
        try {
            return getSqlSession().selectList("com.fangcang.titanjr.rs.dao.RSInvokeConfigDao.queryRSInvokeConfig");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int update(RSInvokeConfig entity) throws DaoException {
        try {
            return getSqlSession().update("com.fangcang.titanjr.rs.dao.RSInvokeConfigDao.updateRSInvokeConfig", entity);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
