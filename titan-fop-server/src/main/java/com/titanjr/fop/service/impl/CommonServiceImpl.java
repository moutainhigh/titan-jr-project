package com.titanjr.fop.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.SMSTemplate;
import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
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

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by zhaoshan on 2017/12/22.
 */
@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    RequestSessionDao requestSessionDao;

    @Autowired
    TitanFinancialSendSMSService titanFinancialSendSMSService;

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

    @Override
    public boolean sendSMSMessage(SMSTemplate template, Object messageTarget) throws ServiceException {
        SendMessageRequest sendCodeRequest = new SendMessageRequest();
        sendCodeRequest.setReceiveAddress("jinrong@fangcang.com");
        sendCodeRequest.setSubject(template.getSubject());
        String content = MessageFormat.format(SMSTemplate.ORG_REG_FAID.getContent(), JSONObject.toJSON(messageTarget).toString());
        sendCodeRequest.setContent(content);
        sendCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
        titanFinancialSendSMSService.asynSendMessage(sendCodeRequest);
        return false;
    }
}
