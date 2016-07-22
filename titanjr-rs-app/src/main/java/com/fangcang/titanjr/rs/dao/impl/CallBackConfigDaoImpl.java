package com.fangcang.titanjr.rs.dao.impl;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.dao.CallBackConfigDao;
import com.fangcang.titanjr.rs.entity.TitanCallBackConfig;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by zhaoshan on 2016/7/21.
 */
public class CallBackConfigDaoImpl extends SqlSessionDaoSupport implements CallBackConfigDao {

    @Override
    public List<TitanCallBackConfig> queryAllCallBackConfig() throws DaoException {
        try {
            return getSqlSession().selectList("com.fangcang.titanjr.rs.dao.CallBackConfigDao.queryAllCallBackConfig");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
