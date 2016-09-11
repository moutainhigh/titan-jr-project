package com.fangcang.titanjr.rs.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.rs.entity.TitanCallBackConfig;

import java.util.List;

/**
 * Created by zhaoshan on 2016/7/21.
 */
public interface CallBackConfigDao {

    List<TitanCallBackConfig> queryAllCallBackConfig() throws DaoException;;
}
