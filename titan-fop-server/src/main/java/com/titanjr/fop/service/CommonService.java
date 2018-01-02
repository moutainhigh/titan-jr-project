package com.titanjr.fop.service;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.request.ExternalSessionGetRequest;
import com.titanjr.fop.response.ExternalSessionGetResponse;

/**
 * Created by zhaoshan on 2017/12/22.
 */
public interface CommonService {

    /**
     * 获取session信息；
     *
     * @param sessionGetRequest
     * @return
     * @throws ServiceException
     */
    ExternalSessionGetResponse getRequestSession(ExternalSessionGetRequest sessionGetRequest) throws ServiceException;
}
