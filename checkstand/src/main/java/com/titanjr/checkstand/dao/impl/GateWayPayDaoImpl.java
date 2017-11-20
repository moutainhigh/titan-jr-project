package com.titanjr.checkstand.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.titanjr.checkstand.dao.GateWayPayDao;
import com.titanjr.checkstand.dto.GateWayPayDTO;
import com.titanjr.checkstand.util.AccessLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhaoshan on 2017/11/17.
 */
public class GateWayPayDaoImpl extends GenericDAOMyBatisImpl implements GateWayPayDao {

    private final static Logger logger = LoggerFactory.getLogger(AccessLimiter.class);

    @Override
    public int batchSaveGateWayPayDTO(List<GateWayPayDTO> gateWayPayDTOList) {
        try {
            return super.insertEntity("com.titanjr.checkstand.dao.GateWayPayDao.batchSaveGateWayPayDTO", gateWayPayDTOList);
        } catch (Exception e) {
            logger.error("batchSaveGateWayPayDTO Error" , e);
            throw new DaoException(e);
        }
    }

}
