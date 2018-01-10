package com.titanjr.fop.service;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.request.ExternalSessionGetRequest;
import com.titanjr.fop.response.ExternalSessionGetResponse;

/**
 * Created by zhaoshan on 2017/12/22.
 */
public interface CommonService {

    /**
     * 创建session；
     * @param sessionGetRequest
     * @return
     * @throws ServiceException
     */
    ExternalSessionGetResponse createRequestSession(ExternalSessionGetRequest sessionGetRequest) throws ServiceException;
}
