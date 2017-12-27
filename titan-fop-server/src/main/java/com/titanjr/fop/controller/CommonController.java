package com.titanjr.fop.controller;

import com.titanjr.fop.entity.RequestSession;
import com.titanjr.fop.response.ExternalSessionGetResponse;
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


    @RequestMapping(value = "/getSession", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getSession(HttpServletRequest request, RedirectAttributes attr) throws Exception {

        ExternalSessionGetResponse sessionGetResponse = new ExternalSessionGetResponse();
        String validResult = validRequestSign(request, sessionGetResponse);
        if (null != validResult) {
            return validResult;
        }

        long random = (long) (Math.random() * 1000000);
        String session = System.currentTimeMillis() + String.valueOf(random);
        sessionGetResponse.setSession(session);
        RequestSession requestSession = new RequestSession();
        requestSession.setAppKey(request.getParameter("appKey"));
        requestSession.setAppSecret(request.getParameter("appSecret"));
        requestSession.setCreateTime(new Date());
        requestSession.setSession(session);

        return toJson(sessionGetResponse);
    }

}
