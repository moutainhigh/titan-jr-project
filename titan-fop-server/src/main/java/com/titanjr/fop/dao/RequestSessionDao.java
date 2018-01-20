package com.titanjr.fop.dao;

import com.fangcang.exception.DaoException;
import com.titanjr.fop.entity.RequestSession;

import java.util.List;

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

    /**
     * 查询session配置验证session是否最正确
     *
     * @param requestSession
     * @return
     * @throws DaoException
     */
    List<RequestSession> queryReqSession(RequestSession requestSession) throws DaoException;

}
