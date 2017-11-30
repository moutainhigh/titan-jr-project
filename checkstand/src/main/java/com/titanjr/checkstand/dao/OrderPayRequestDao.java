package com.titanjr.checkstand.dao;

import com.titanjr.checkstand.dto.OrderPayRequestDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2017/11/17.
 */
public interface OrderPayRequestDao {

    /**
     * 插入
     * @param gateWayPayDTOList
     * @return
     */
    int batchSaveGateWayPayDTO(List<OrderPayRequestDTO> gateWayPayDTOList);

}
