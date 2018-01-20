package com.titanjr.fop.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.titanjr.fop.api.FopController;
import com.titanjr.fop.dao.RequestSessionDao;
import com.titanjr.fop.entity.RequestSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public class RequestSessionDaoImpl extends GenericDAOMyBatisImpl implements RequestSessionDao {

    private final static Logger logger = LoggerFactory.getLogger(FopController.class);

    @Override
    public int saveRequestSession(RequestSession requestSession) throws DaoException {
        try {
            return super.insertEntity("com.titanjr.fop.dao.RequestSessionDao.saveRequestSession", requestSession);
        } catch (Exception e) {
            logger.error("saveRequestSession Error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<RequestSession> queryReqSession(RequestSession requestSession) throws DaoException {
        try {
            return super.selectList("com.titanjr.fop.dao.RequestSessionDao.queryReqSession", requestSession);
        } catch (Exception e) {
            logger.error("getReqSession Error", e);
            throw new DaoException(e);
        }
    }
}
