package com.titanjr.fop.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;
import com.titanjr.fop.dto.TitanAccountDetailDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public interface TitanOrderDao {

    /**
     * 查询退款单
     * @param refundDTO
     * @return
     */
    List<RefundDTO> queryRefundDTOList(RefundDTO refundDTO) throws DaoException;

    /**
     * 查询交易记账记录
     * @param param
     * @return
     * @throws DaoException
     */
    List<TitanAccountDetailDTO> selectAccountDetail(TitanAccountDetailParam param) throws DaoException;
}
