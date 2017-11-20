package com.titanjr.checkstand.dao;

import com.titanjr.checkstand.dto.GateWayPayDTO;

import java.util.List;

/**
 * Created by zhaoshan on 2017/11/17.
 */
public interface GateWayPayDao {

    /**
     * 插入
     * @param gateWayPayDTOList
     * @return
     */
    int batchSaveGateWayPayDTO(List<GateWayPayDTO> gateWayPayDTOList);

}
