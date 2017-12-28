package com.titanjr.fop.dao;

import com.fangcang.exception.DaoException;
import com.titanjr.fop.entity.RequestSession;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public interface RequestSessionDao {

    /**
     * 要保存在本地
     *
     * @param requestSession
     * @return
     */
    int saveRequestSession(RequestSession requestSession) throws DaoException;
}
