package com.titanjr.fop.controller;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.dao.RequestSessionDao;
import com.titanjr.fop.entity.RequestSession;
import com.titanjr.fop.request.ExternalSessionGetRequest;
import com.titanjr.fop.response.ExternalSessionGetResponse;
import com.titanjr.fop.util.BeanUtils;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by zhaoshan on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    RequestSessionDao requestSessionDao;

    @RequestMapping(value = "/getSession", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getSession(HttpServletRequest request, RedirectAttributes attr) throws Exception {

        ExternalSessionGetResponse sessionGetResponse = new ExternalSessionGetResponse();
        String validResult = validRequestSign(request, sessionGetResponse);
        if (null != validResult) {
            return validResult;
        }

        ExternalSessionGetRequest sessionGetRequest = BeanUtils.switch2RequestDTO(ExternalSessionGetRequest.class, request);
        if (null == sessionGetRequest) {
            return getConvertErrorResp(sessionGetResponse);
        }

        long random = (long) (Math.random() * 1000000);
        String session = System.currentTimeMillis() + String.valueOf(random);
        RequestSession requestSession = new RequestSession();
        requestSession.setAppKey(request.getParameter("appKey"));
        requestSession.setAppSecret(request.getAttribute("appSecret").toString());
        requestSession.setCreateTime(new Date());
        requestSession.setSession(session);

        int result = requestSessionDao.saveRequestSession(requestSession);
        if (result < 1) {
            sessionGetResponse.setErrorCode(ReturnCodeEnum.CODE_SYS_ERROR.getCode());
            sessionGetResponse.setMsg(ReturnCodeEnum.CODE_SYS_ERROR.getMsg());
            return toJson(sessionGetResponse);
        }
        sessionGetResponse.setSession(session);

        logger.info("get session request:{},session result:{}", JSONSerializer.toJSON(sessionGetRequest).toString(), session);
        return toJson(sessionGetResponse);
    }

}
