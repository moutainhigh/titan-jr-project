package com.fangcang.titanjr.rs.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.entity.RSInvokeConfig;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public interface RSInvokeConfigDao {

    List<RSInvokeConfig> queryRSInvokeConfig() throws DaoException;

    int update(RSInvokeConfig entity) throws DaoException;
}
