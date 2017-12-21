package com.titanjr.fop.controller;

import com.titanjr.fop.constants.InterfaceConfigEnum;
import com.titanjr.fop.constants.InterfaceURlConfig;
import com.titanjr.fop.response.SessionGetResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {


    @RequestMapping(value = "/getSession", method ={RequestMethod.POST,RequestMethod.GET},produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String fopsdk(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        SessionGetResponse sessionGetResponse = new SessionGetResponse();
        sessionGetResponse.setSession("session");
        return toJson(sessionGetResponse);
    }

}
