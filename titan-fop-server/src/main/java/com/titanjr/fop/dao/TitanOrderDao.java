package com.titanjr.fop.dao;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RefundDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public interface TitanOrderDao {

    /**
     * 查询
     * @param refundDTO
     * @return
     */
    List<RefundDTO> queryRefundDTOList(RefundDTO refundDTO) throws DaoException;

}
