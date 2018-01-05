package com.titanjr.fop.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.titanjr.fop.dao.TitanOrderDao;

import java.util.List;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class TitanOrderDaoImpl extends GenericDAOMyBatisImpl implements TitanOrderDao {

    @Override
    public List<RefundDTO> queryRefundDTOList(RefundDTO refundDTO) throws DaoException {
        try {
            return super.selectList("com.titanjr.fop.dao.TitanOrderDao.queryRefundDTOList", refundDTO);
        } catch (Exception e) {
            logger.error("queryRefundDTO Error", e);
            throw new DaoException(e);
        }
    }
}
