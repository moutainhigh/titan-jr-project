package com.fangcang.titanjr.rs.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.entity.TitanPayMethod;

import java.util.List;

/**
 * 融数支付方式与配置查询
 * Created by zhaoshan on 2016/6/3.
 */
public interface RSPayMethodDao {

    /**
     * 初始化时查询融数所有的支付配置
     * 只有一条记录
     * @return
     * @throws DaoException
     */
    public List<TitanPayMethod> queryAllTitanPayMethod() throws DaoException;
}
