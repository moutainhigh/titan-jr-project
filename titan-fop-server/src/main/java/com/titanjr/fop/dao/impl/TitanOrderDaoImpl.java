package com.titanjr.fop.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;
import com.titanjr.fop.dao.TitanOrderDao;
import com.titanjr.fop.dto.TitanAccountDetailDTO;

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


    @Override
    public List<TitanAccountDetailDTO> selectAccountDetail(TitanAccountDetailParam param) throws DaoException {
        try {
            return super.selectList("com.titanjr.fop.dao.TitanOrderDao.selectAccountDetail", param);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
