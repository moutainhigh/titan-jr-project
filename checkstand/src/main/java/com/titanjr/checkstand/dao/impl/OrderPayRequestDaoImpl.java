package com.titanjr.checkstand.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.titanjr.checkstand.dao.OrderPayRequestDao;
import com.titanjr.checkstand.dto.OrderPayRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhaoshan on 2017/11/17.
 */
public class OrderPayRequestDaoImpl extends GenericDAOMyBatisImpl implements OrderPayRequestDao {

    private final static Logger logger = LoggerFactory.getLogger(OrderPayRequestDaoImpl.class);

    @Override
    public int batchSaveGateWayPayDTO(List<OrderPayRequestDTO> orderPayRequestList) {
        try {
            return super.insertEntity("com.titanjr.checkstand.dao.OrderPayRequestDao.batchSave", orderPayRequestList);
        } catch (Exception e) {
            logger.error("batchSaveGateWayPayDTO Error" , e);
            throw new DaoException(e);
        }
    }

}
