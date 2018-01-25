package com.titanjr.fop.service.impl;


import com.fangcang.exception.ServiceException;
import com.titanjr.fop.constants.CommonConstants;
import com.titanjr.fop.dao.RequestSessionDao;
import com.titanjr.fop.entity.RequestSession;
import com.titanjr.fop.request.ExternalSessionGetRequest;
import com.titanjr.fop.response.ExternalSessionGetResponse;
import com.titanjr.fop.service.CommonService;
import com.titanjr.fop.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/12/22.
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    RequestSessionDao requestSessionDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public ExternalSessionGetResponse createRequestSession(ExternalSessionGetRequest sessionGetRequest) throws ServiceException {

        ExternalSessionGetResponse sessionGetResponse = new ExternalSessionGetResponse();
        long random = (long) (Math.random() * 1000000);
        String session = System.currentTimeMillis() + String.valueOf(random);
        RequestSession requestSession = new RequestSession();
        requestSession.setAppKey(sessionGetRequest.getAppKey());
        requestSession.setAppSecret(CommonConstants.appSecret);
        requestSession.setCreateTime(new Date());
        requestSession.setSession(session);

        int result = requestSessionDao.saveRequestSession(requestSession);
        if (result < 1) {
            ResponseUtils.getSysErrorResp(sessionGetResponse);
            return sessionGetResponse;
        }
        sessionGetResponse.setSession(session);
        return sessionGetResponse;
    }
}
