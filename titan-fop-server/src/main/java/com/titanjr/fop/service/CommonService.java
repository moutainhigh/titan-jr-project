package com.titanjr.fop.service;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.common.util.SMSTemplate;
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

    /**
     * 统一发送邮件
     * @param template
     * @param messageTarget
     * @return
     * @throws ServiceException
     */
    boolean sendSMSMessage(SMSTemplate template, Object messageTarget) throws ServiceException;
}
