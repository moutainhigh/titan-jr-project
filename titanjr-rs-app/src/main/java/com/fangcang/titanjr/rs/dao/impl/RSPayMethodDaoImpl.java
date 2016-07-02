package com.fangcang.titanjr.rs.dao.impl;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.dao.RSPayMethodDao;
import com.fangcang.titanjr.rs.entity.TitanPayMethod;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by zhaoshan on 2016/6/3.
 */
public class RSPayMethodDaoImpl extends SqlSessionDaoSupport implements RSPayMethodDao {

    @Override
    public List<TitanPayMethod> queryAllTitanPayMethod() throws DaoException {
        try {
            return getSqlSession().selectList("com.fangcang.titanjr.rs.dao.RSPayMethodDao.queryAllTitanPayMethod");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
