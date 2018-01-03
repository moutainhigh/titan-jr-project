package com.titanjr.fop.service;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;

/**
 * Created by zhaoshan on 2018/1/3.
 */
public interface OrderOperService {

    /**
     * 返回操作后的订单号，返回null代表操作失败
     *
     * @param orderOperRequest
     * @return
     * @throws ServiceException
     */
    String operateOrder(WheatfieldOrderOperRequest orderOperRequest) throws ServiceException;
}
