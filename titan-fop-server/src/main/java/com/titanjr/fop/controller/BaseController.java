package com.titanjr.fop.controller;

import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONSerializer;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public class BaseController {

    ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        requestLocal.set(request);
        responseLocal.set(response);
    }

    protected HttpServletRequest getRequest() {

        return requestLocal.get();
    }

    protected HttpServletResponse getResponse() {

        return responseLocal.get();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }


    public String toJson(Object object) {
        return JSONSerializer.toJSON(object).toString();
    }

    public String validRequestSign(HttpServletRequest request, FopResponse fopResponse) {
        if (request.getParameter("signValid").equals("false")) {
            fopResponse.setErrorCode("");
            fopResponse.setMsg("");
            return toJson(fopResponse);
        }
        return null;
    }
}
